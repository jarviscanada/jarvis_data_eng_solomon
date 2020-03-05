package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;

import static org.junit.Assert.*;

public class OrderServiceTest {
  OrderService orderService;
  TestConfig config;
  SecurityOrderDao orderDao;
  AccountDao accountDao;
  PositionDao positionDao;
  QuoteDao quoteDao;
  QuoteService quoteService;
  MarketDataDao marketDataDao;
  TraderDao traderDao;
  MarketOrderDto orderOne, orderTwo;
  Quote quote;
  Trader trader;
  Account account;
  
  @Before
  public void setUp () throws Exception {
    config = new TestConfig();
    orderDao = new SecurityOrderDao(config.dataSource());
    accountDao = new AccountDao(config.dataSource());
    positionDao = new PositionDao(config.dataSource());
    traderDao = new TraderDao(config.dataSource());
    quoteDao = new QuoteDao(config.dataSource());
    marketDataDao = new MarketDataDao(config.httpClientConnectionManager(),
        config.marketDataConfig());
    
    orderService = new OrderService(accountDao, orderDao, quoteDao, positionDao);
    quoteService = new QuoteService(quoteDao, marketDataDao);
  
    trader = new Trader();
    trader.setFirstName("Solomon");
    trader.setLastName("Blake");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setCountry("Canada");
    trader.setEmail("solomoncblake@gmail.com");
    traderDao.save(trader);
    
    quote = quoteService.saveQuotes(Arrays.asList("TSLA")).get(0);
  
    account = new Account();
    account.setTraderId(trader.getId());
    account.setAmount(quote.getAskPrice() * 10);
    accountDao.save(account);
    
    orderOne = new MarketOrderDto(quote.getTicker(), 10, true, account.getId());
    orderTwo = new MarketOrderDto(quote.getTicker(), 5, false,
        account.getId());
  }
  
  @After
  public void tearDown () throws Exception {
    orderDao.deleteAll();
    quoteDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
  }
  
  @Test
  public void executeMarketOrder () {
    
    orderService.executeMarketOrder(orderOne);
    
    orderService.executeMarketOrder(orderTwo);
    
    assertTrue(account.getAmount() > 0);
  
  }
}