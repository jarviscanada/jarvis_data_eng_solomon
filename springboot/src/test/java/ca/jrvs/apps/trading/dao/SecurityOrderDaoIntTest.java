package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class SecurityOrderDaoIntTest {
  
  @Autowired
  SecurityOrderDao securityOrderDao;
  @Autowired
  AccountDao accountDao;
  @Autowired
  TraderDao traderDao;
  @Autowired
  QuoteDao quoteDao;
  TestConfig config;
  Quote quote, secondQuote;
  Trader trader;
  Account account;
  SecurityOrder securityOrder, secondOrder;

  @Before
  public void setUp () {
    config = new TestConfig();
    
    quoteDao = new QuoteDao(config.dataSource());
    traderDao = new TraderDao(config.dataSource());
    accountDao = new AccountDao(config.dataSource());
    securityOrderDao = new SecurityOrderDao(config.dataSource());
    
    quote = new Quote();
    quote.setID("TSLA");
    quote.setTicker("TSLA");
    quote.setAskPrice(12d);
    quote.setAskSize(BigInteger.valueOf(100));
    quote.setBidPrice(12.1d);
    quote.setBidSize(BigInteger.valueOf(100));
    quote.setLastPrice(13d);
    quoteDao.save(quote);
  
    secondQuote = new Quote();
    secondQuote.setID("MSFT");
    secondQuote.setTicker("MSFT");
    secondQuote.setAskPrice(8d);
    secondQuote.setAskSize(BigInteger.valueOf(100));
    secondQuote.setBidPrice(8.1d);
    secondQuote.setBidSize(BigInteger.valueOf(100));
    secondQuote.setLastPrice(8.5d);
    quoteDao.save(secondQuote);
    
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
    securityOrder.setNotes("");
    securityOrder.setPrice(10.0);
    securityOrder.setSize(12);
    securityOrder.setTicker(quote.getTicker());
    securityOrder.setStatus("OPEN");
  
    secondOrder = new SecurityOrder();
    secondOrder.setAccountId(account.getId());
    secondOrder.setNotes("");
    secondOrder.setPrice(5.0);
    secondOrder.setSize(1);
    secondOrder.setTicker(secondQuote.getTicker());
    secondOrder.setStatus("FILLED");
  }
  
  @After
  public void tearDown () {
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
  }
  
  @Test
  public void integrationTest () {
    SecurityOrder savedSecurityOrder = securityOrderDao.save(securityOrder);
    assertNotEquals(savedSecurityOrder.getId().intValue(), 0);
  
    if (securityOrderDao.existsById(securityOrder.getId()) == false) {
      fail();
    }
  
    assertEquals(securityOrderDao.findById(secondOrder.getId()), Optional.empty());
  
    SecurityOrder secondSaved = ((List<SecurityOrder>)
                               securityOrderDao.saveAll(
                                   Collections.singletonList(secondOrder))).get(0);
  
    List<SecurityOrder> foundSecurityOrders =
        (List<SecurityOrder>) securityOrderDao.findAllById(
            Arrays.asList(savedSecurityOrder.getId(), secondOrder.getId()));
  
    List<SecurityOrder> findAllSecurityOrders= (List<SecurityOrder>) securityOrderDao.findAll();
  
    
    List<SecurityOrder> foundByAccountId = securityOrderDao.findAllByAccountId(account.getId());
    
    assertEquals(foundSecurityOrders.size(), findAllSecurityOrders.size());
  
    for (int i = 0; i < foundSecurityOrders.size(); i++) {
      assertEquals(foundSecurityOrders.get(i).getPrice(), findAllSecurityOrders.get(i).getPrice());
      assertEquals(foundByAccountId.get(i).getPrice(), findAllSecurityOrders.get(i).getPrice());
    }
  
    secondSaved.setPrice(1337.0);
    secondSaved.setSize(7);
    SecurityOrder updatedSecond = securityOrderDao.save(secondSaved);
    assertEquals(updatedSecond.getSize(), secondSaved.getSize());
  
    securityOrderDao.deleteById(secondOrder.getId());
  
    securityOrderDao.deleteAll();
  
    assertEquals(securityOrderDao.count(), 0);
  }
}