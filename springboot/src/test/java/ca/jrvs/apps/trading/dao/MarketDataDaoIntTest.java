package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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