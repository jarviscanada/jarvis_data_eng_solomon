package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.AppConfig;
import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.math.BigInteger;
import java.util.Arrays;

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
    savedQuote.setAskPrices(10d);
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
    newQuote.setAskPrices(12d);
    newQuote.setAskSize(BigInteger.valueOf(100));
    newQuote.setBidPrice(12.1d);
    newQuote.setBidSize(BigInteger.valueOf(100));
    newQuote.setLastPrice(13d);
    quoteDao.save(newQuote);
  
    assertEquals(2, quoteDao.count());
    
    assertNotNull(quoteDao.findAllById(Arrays.asList("TSLA", "AAPL")));
    assertNotNull(quoteDao.findAll());
    
    quoteDao.deleteById(newQuote.getId());
    assertNull(newQuote);
  }
}