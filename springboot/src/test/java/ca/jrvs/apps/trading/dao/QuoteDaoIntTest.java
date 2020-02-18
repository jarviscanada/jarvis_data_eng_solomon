package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {
  
  @Autowired
  QuoteDao quoteDao;
  Quote savedQuote, newQuote, thirdQuote;
  
  @Before
  public void setUp () throws Exception {
    quoteDao = new QuoteDao(new TestConfig().dataSource());
    savedQuote = new Quote();
    newQuote = new Quote();
    thirdQuote = new Quote();
  }
  
  @Before
  public void insertOne () {
    savedQuote.setID("AAPL");
    savedQuote.setTicker("AAPL");
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(BigInteger.valueOf(10));
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(BigInteger.valueOf(10));
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @After
  public void deleteOne () {
    quoteDao.deleteAll();
  }
  
  @Test
  public void integrationTest () {
    assertTrue(quoteDao.existsById(savedQuote.getId()));
    assertEquals(1, quoteDao.count());
  
    newQuote.setID("TSLA");
    newQuote.setTicker("TSLA");
    newQuote.setAskPrice(12d);
    newQuote.setAskSize(BigInteger.valueOf(100));
    newQuote.setBidPrice(12.1d);
    newQuote.setBidSize(BigInteger.valueOf(100));
    newQuote.setLastPrice(13d);
    
    thirdQuote.setID("MSFT");
    thirdQuote.setTicker("MSFT");
    thirdQuote.setAskPrice(11d);
    thirdQuote.setAskSize(BigInteger.valueOf(10));
    thirdQuote.setBidPrice(11.1d);
    thirdQuote.setBidSize(BigInteger.valueOf(10));
    thirdQuote.setLastPrice(12d);
    
    quoteDao.saveAll(Arrays.asList(newQuote, thirdQuote));
    assertEquals(3, quoteDao.count());
  
    assertNotNull(quoteDao.findAll());
  
    savedQuote.setBidSize(BigInteger.valueOf(20));
    assertNotNull(quoteDao.save(savedQuote));
    
    assertNotNull(quoteDao.findById(thirdQuote.getId()));
    
    assertEquals(Optional.empty(), quoteDao.findById("GOOG"));
  
    quoteDao.deleteById(newQuote.getId());
    assertFalse(quoteDao.existsById(newQuote.getId()));
    
    newQuote.setTicker("invalidTicker");
    newQuote.setID("invalidTicker");
    try {
      quoteDao.save(newQuote);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
      return;
    }
    fail();
  }
}