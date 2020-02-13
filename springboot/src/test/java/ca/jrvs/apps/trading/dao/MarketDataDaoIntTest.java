package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoIntTest {
  
  MarketDataDao marketDataDao;
  PoolingHttpClientConnectionManager connectionManager;
  MarketDataConfig config;
  
  @Before
  public void setUp () throws Exception {
    connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(50);
    connectionManager.setDefaultMaxPerRoute(50);
  
    config = new MarketDataConfig();
    config.setToken(System.getenv("IEX_PUB_TOKEN"));
    config.setHost("https://cloud.iexapis.com/v1/");
  
    marketDataDao = new MarketDataDao(connectionManager, config);
  }
  
  @Test
  public void findIexQuotesByTicker () {
    List<String> tickerList = Arrays.asList("MSFT", "AAPL", "TSLA");
    List<IexQuote> iexQuotes = marketDataDao.findAllById(tickerList);
    assertEquals(iexQuotes.size(), 3);
    
    iexQuotes.forEach(Assert::assertNotNull);
  }
  
}