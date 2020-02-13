package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import com.fasterxml.jackson.databind.*;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.*;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {
  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;
  
  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;
  
  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
                       MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }
  /**
   *
   * @param ticker symbol for IEX Quote
   * @return Optional IexQuote object if found
   */
  @Override
  public Optional<IexQuote> findById (String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));
    
    if(quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }
  
  /**
   * @param iterable containing ticker symbols
   * @return List of IEX Quotes
   */
  @Override
  public List<IexQuote> findAllById (Iterable<String> iterable) {
    List<IexQuote> quotes = new ArrayList<>();
    StringBuilder tickerListString = new StringBuilder();
  
    for (String ticker : iterable) {
      tickerListString.append(ticker);
      if (iterable.iterator().hasNext()) tickerListString.append(",");
    }
    
    String formedURL = String.format(IEX_BATCH_URL, tickerListString.toString().toUpperCase());
    
    try {
      Optional<String> jsonEntity = executeHttpGet(formedURL);
      if (jsonEntity.isPresent()) {
        JSONObject entityJson = new JSONObject(jsonEntity.get());
        ObjectMapper mapper = new ObjectMapper()
                                  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                                      false);
        JSONObject retrievedQuotes;
        for (String tickerSymbol : iterable) {
          try {
            retrievedQuotes = entityJson.getJSONObject(tickerSymbol);
            quotes.add(mapper.readValue(retrievedQuotes.getJSONObject("quote").toString(),
                IexQuote.class));
          } catch (IOException e) {
            throw new RuntimeJsonMappingException("Unable to map JSONObject to IexQuote");
          }
        }
      } else {
        throw new IllegalArgumentException("Empty JSON Entity returned");
      }
    } catch (IOException e) {
      throw new RuntimeException("Unable to form HTTP response");
    } catch (HttpException e) {
      throw new RuntimeException("Unable to execute HTTP GET");
    }
    return quotes;
  }
  
  /**
   *
   * @return
   */
  private CloseableHttpClient getHttpClient () {
    return HttpClients.custom()
               .setConnectionManager(httpClientConnectionManager)
               .setConnectionManagerShared(true)
               .build();
  }
  
  /**
   * @param/ Request URI as a string
   * @return Optional String containing JSON if received
   */
  private Optional<String> executeHttpGet (String uri) throws IOException, HttpException {
    Optional<String> httpEntityString;
    CloseableHttpResponse httpResponse;
    
    try {
      httpResponse = getHttpClient().execute(new HttpGet(uri));
    } catch (IOException e) {
      throw new IOException("Unable to form HTTP Response");
    }
    
    if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
      throw new HttpException("Received status code: "
                                  + httpResponse.getStatusLine().getStatusCode());
    }
    
    try {
      httpEntityString = Optional.ofNullable(EntityUtils.toString(httpResponse.getEntity()));
    } catch (IOException ex) {
      throw new IOException("Unable to form HTTP entity from HTTP response");
    }
    
    return httpEntityString;
  }
  
  @Override
  public boolean existsById (String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll () {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count () {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public void deleteById (String s) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public void delete (IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public void deleteAll (Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public void deleteAll () {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public <S extends IexQuote> S save (S s) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public <S extends IexQuote> Iterable<S> saveAll (Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
