package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.TraderAccountView;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class TraderAccountServiceIntTest {
  
  TraderAccountService traderAccountService;
  TestConfig config;
  Trader trader;

  @Before
  public void setUp () {
    config = new TestConfig();
    traderAccountService = new TraderAccountService(new TraderDao(config.dataSource()),
        new AccountDao(config.dataSource()), new PositionDao(config.dataSource()),
        new SecurityOrderDao(config.dataSource()));
    
    trader = new Trader();
    trader.setFirstName("Solomon");
    trader.setLastName("Blake");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setCountry("Canada");
    trader.setEmail("solomoncblake@gmail.com");
  }
  
  @Test
  public void integrationTest () {
    TraderAccountView traderAccountView = traderAccountService.createTraderAndAccount(trader);
    
    assertEquals(traderAccountView.getTrader(), trader);
    
    try {
      traderAccountService.deposit(traderAccountView.getTrader().getId(), 0.);
    } catch (Exception e) {
      assert(true);
    }
    
    traderAccountService.deposit(traderAccountView.getTrader().getId(), 1200.);
  
    try {
      traderAccountService.withdraw(traderAccountView.getTrader().getId(), -1.);
    } catch (Exception e) {
      assert(true);
    }
  
    try {
      traderAccountService.withdraw(-1, 1.);
    } catch (Exception e) {
      assert(true);
    }
    
    traderAccountService.withdraw(traderAccountView.getTrader().getId(), 1199.);
    
    assertTrue(traderAccountView.getAccount().getAmount() == 1.);
    
    traderAccountService.deleteTraderById(trader.getId());
  }
}