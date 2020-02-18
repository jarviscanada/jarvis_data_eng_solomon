package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
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
  TestConfig config;
  
  @Before
  public void setUp () throws Exception {
    config = new TestConfig();
    
    marketDataDao = new MarketDataDao(config.httpClientConnectionManager(),
        config.marketDataConfig());
  }
  
  @Test
  public void integrationTest () {
    List<String> tickerList = Arrays.asList("MSFT", "AAPL", "TSLA");
    List<IexQuote> iexQuotes = marketDataDao.findAllById(tickerList);
    assertEquals(iexQuotes.size(), 3);
    
   for (String ticker : tickerList) {
    assertTrue(ticker.equalsIgnoreCase(marketDataDao.findById(ticker).get().getSymbol()));
   }
   
  }
  
}