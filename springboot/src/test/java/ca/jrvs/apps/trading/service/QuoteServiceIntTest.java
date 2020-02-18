package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {
  
  @Autowired
  QuoteService quoteService;
  QuoteDao quoteDao;
  MarketDataDao marketDataDao;
  TestConfig config;
  List<String> tickerList;
  
  @Before
  public void setUp () {
    config = new TestConfig();
    quoteDao = new QuoteDao(config.dataSource());
    marketDataDao = new MarketDataDao(config.httpClientConnectionManager(),
        config.marketDataConfig());
    quoteService = new QuoteService(quoteDao, marketDataDao);
  }
  
  @After
  public void tearDown () {
    quoteDao.deleteAll();
  }
  
  @Test
  public void integrationTest () {
    tickerList = Arrays.asList("AMD", "tsla", "QcOm", "??");
    
    List<IexQuote> foundQuotes = new ArrayList<>();
    for (String string : tickerList) {
       try {
         foundQuotes.add(quoteService.findIexQuoteByTicker(string));
       } catch (IllegalArgumentException e) {
         if(string.equals("??")) {
           assert(true);
           break;
         }
         fail();
       }
    }
    foundQuotes.forEach(Assert::assertNotNull);
    
    tickerList = tickerList.subList(0, 3);
    List<Quote> savedQuotes = quoteService.saveQuotes(tickerList);
    
    assertEquals(foundQuotes.size(), savedQuotes.size());
    for (int i = 0; i < savedQuotes.size(); i++) {
      assertEquals(savedQuotes.get(i).getAskPrice(), foundQuotes.get(i).getIexAskPrice());
    }
    
    List<Quote> retrievedQuotes = quoteService.findAllQuotes();
  
    assertEquals(retrievedQuotes.size(), savedQuotes.size());
    for (int i = 0; i < retrievedQuotes.size(); i++) {
      assertEquals(retrievedQuotes.get(i).getLastPrice(), savedQuotes.get(i).getLastPrice());
    }
    
    List<Quote> updatedQuotes =  quoteService.updateMarketData();
  
    assertEquals(updatedQuotes.size(), savedQuotes.size());
    for (int i = 0; i < updatedQuotes.size(); i++) {
      assertNotEquals(updatedQuotes.get(i), savedQuotes.get(i));
    }
    
    updatedQuotes.forEach(quoteService::saveQuote);
    
    for (int i = 0; i < updatedQuotes.size(); i++) {
      assertEquals(updatedQuotes.get(i).getBidSize(), savedQuotes.get(i).getBidSize());
    }
    
  }
}