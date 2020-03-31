package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class PositionDaoIntTest {
  
  @Autowired
  PositionDao positionDao;
  @Autowired
  SecurityOrderDao securityOrderDao;
  @Autowired
  AccountDao accountDao;
  @Autowired
  TraderDao traderDao;
  @Autowired
  QuoteDao quoteDao;
  TestConfig config;

  Position position;
  SecurityOrder securityOrder;
  Account account, secondAccount;
  Trader trader;
  Quote quote;

  @Before
  public void setUp () {
    config = new TestConfig();
    
    positionDao = new PositionDao(config.dataSource());
    securityOrderDao = new SecurityOrderDao(config.dataSource());
    accountDao = new AccountDao(config.dataSource());
    traderDao = new TraderDao(config.dataSource());
    quoteDao = new QuoteDao(config.dataSource());
  
    quote = new Quote();
    quote.setID("TSLA");
    quote.setTicker("TSLA");
    quote.setAskPrice(12d);
    quote.setAskSize(BigInteger.valueOf(100));
    quote.setBidPrice(12.1d);
    quote.setBidSize(BigInteger.valueOf(100));
    quote.setLastPrice(13d);
    quoteDao.save(quote);
  
    trader = new Trader();
    trader.setFirstName("Solomon");
    trader.setLastName("Blake");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setCountry("Canada");
    trader.setEmail("solomoncblake@gmail.com");
    traderDao.save(trader);
  
    account = new Account();
    account.setAmount(12500.0);
    account.setTraderId(trader.getId());
    accountDao.save(account);
  
    securityOrder = new SecurityOrder();
    securityOrder.setAccountId(account.getId());
    securityOrder.setNotes("notes go here.");
    securityOrder.setPrice(10.0);
    securityOrder.setSize(12);
    securityOrder.setTicker(quote.getTicker());
    securityOrder.setStatus("FILLED");
    securityOrderDao.save(securityOrder);
  }
  
  @After
  public void tearDown () {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
  }
  
  @Test
  public void integrationTest () {
    Optional<Position> foundPosition = positionDao.findAllByAccountIdAndTicker(
        account.getId(), quote.getTicker());
    assertNotNull(foundPosition);
    
    assertEquals(quote.getTicker(),
        positionDao.findByAccountId(account.getId()).get(0).getTicker());
  }
}