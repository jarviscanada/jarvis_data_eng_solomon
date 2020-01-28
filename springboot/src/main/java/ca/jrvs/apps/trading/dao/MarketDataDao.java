package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
  
  @Override
  public Optional<IexQuote> findById (String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singleton(ticker));
    
    if(quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }
  
  @Override
  public Iterable<IexQuote> findAllById (Iterable<String> iterable) {
    return null;
  }
  
  private CloseableHttpClient getHttpClient () {
    return HttpClients.custom().setConnectionManagerShared(true).build();
  }

  @Override
  public <S extends IexQuote> S save (S s) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public <S extends IexQuote> Iterable<S> saveAll (Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
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
}
