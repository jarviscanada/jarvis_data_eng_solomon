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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {
  
  @Autowired
  QuoteDao quoteDao;
  Quote savedQuote, newQuote;
  
  @Before
  public void setUp () throws Exception {
    quoteDao = new QuoteDao(new TestConfig().dataSource());
    savedQuote = new Quote();
    newQuote = new Quote();
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
    quoteDao.deleteById(savedQuote.getId());
  }
  
  @Test
  public void intTest () {
    assertTrue(quoteDao.existsById(savedQuote.getId()));
    assertEquals(1, quoteDao.count());
  
    newQuote.setID("TSLA");
    newQuote.setTicker("TSLA");
    newQuote.setAskPrice(12d);
    newQuote.setAskSize(BigInteger.valueOf(100));
    newQuote.setBidPrice(12.1d);
    newQuote.setBidSize(BigInteger.valueOf(100));
    newQuote.setLastPrice(13d);
    quoteDao.save(newQuote);
  
    assertEquals(2, quoteDao.count());
    
    assertNotNull(quoteDao.findAll());
    
    quoteDao.deleteById(newQuote.getId());
    assertFalse(quoteDao.existsById(newQuote.getId()));
  }
}