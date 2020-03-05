package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

  public class AccountDaoIntTest {
  @Autowired
  AccountDao accountDao;
  @Autowired
  TraderDao traderDao;
  TestConfig config;
  
  @Before
  public void setUp () {
    config = new TestConfig();
    accountDao = new AccountDao(config.dataSource());
    traderDao = new TraderDao(config.dataSource());
  }
  
  @After
  public void tearDown () {
    traderDao.deleteAll();
  }
  
  @Test
  public void integrationTest () {
    Trader trader = new Trader();
    trader.setFirstName("Solomon");
    trader.setLastName("Blake");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setCountry("Canada");
    trader.setEmail("solomoncblake@gmail.com");
    traderDao.save(trader);
    
    Trader secondtrader = new Trader();
    secondtrader.setFirstName("Solomonn");
    secondtrader.setLastName("Blake");
    secondtrader.setDob(new Date(System.currentTimeMillis()));
    secondtrader.setCountry("Canada");
    secondtrader.setEmail("solomon_c_blake@gmail.com");
    traderDao.save(secondtrader);
    
    Account account = new Account();
    account.setAmount(0.0);
    account.setTraderId(trader.getId());
    
    Account savedAccount = accountDao.save(account);
    assertNotEquals(savedAccount.getId().intValue(), 0);
    
    if (accountDao.existsById(account.getId()) == false) {
      fail();
    }
    
    Account second = new Account();
    second.setAmount(25.0);
    second.setTraderId(secondtrader.getId());
    
    assertEquals(accountDao.findById(second.getId()), Optional.empty());
    
    Account secondSaved = ((List<Account>)
                               accountDao.saveAll(Collections.singletonList(second))).get(0);
    
    List<Account> foundAccounts =
        (List<Account>) accountDao.findAllById(Arrays.asList(savedAccount.getId(),
            secondSaved.getId()));
    
    List<Account> findAllAccounts = (List<Account>) accountDao.findAll();
    
    assertEquals(foundAccounts.size(), findAllAccounts.size());
    
    for (int i = 0; i < foundAccounts.size(); i++) {
      assertEquals(foundAccounts.get(i).getAmount(), findAllAccounts.get(i).getAmount());
    }
    
    assertEquals(accountDao.findByTraderId(trader.getId()).getId(), account.getId());
    
    assertNotEquals(0.0, accountDao.updateAmountById(savedAccount, 1337.0));
    
    accountDao.deleteById(savedAccount.getId());
    
    accountDao.deleteAll();
    
    assertEquals(accountDao.count(), 0);
  }
}