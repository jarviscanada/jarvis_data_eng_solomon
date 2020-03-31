package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TraderDaoIntTest {
  @Autowired
  TraderDao traderDao;
  TestConfig config;

  @Before
  public void setUp () {
    config = new TestConfig();
    traderDao = new TraderDao(config.dataSource());
  }
  
  @Test
  public void integrationTest () {
    Trader trader = new Trader();
    trader.setFirstName("Solomon");
    trader.setLastName("Blake");
    trader.setDob(new Date(System.currentTimeMillis()));
    trader.setCountry("Canada");
    trader.setEmail("solomoncblake@gmail.com");
  
    Trader second = new Trader();
    second.setFirstName("Blake");
    second.setLastName("Solomon");
    second.setDob(new Date(System.currentTimeMillis()));
    second.setCountry("Adanac");
    second.setEmail("blakecsolomon@gmail.com");
    
    Trader savedTrader = traderDao.save(trader);
    assertNotEquals(savedTrader.getId().intValue(), 0);
    
    if (traderDao.existsById(trader.getId()) == false) {
      fail();
    }
    
    assertEquals(traderDao.findById(second.getId()), Optional.empty());
  
    Trader secondSaved = ((List<Trader>)
                              traderDao.saveAll(Collections.singletonList(second))).get(0);
    
    List<Trader> foundTraders =
        (List<Trader>) traderDao.findAllById(Arrays.asList(savedTrader.getId(),
        secondSaved.getId()));
    
    List<Trader> findAllTraders = (List<Trader>) traderDao.findAll();
    
    assertEquals(foundTraders.size(), findAllTraders.size());
  
    for (int i = 0; i < foundTraders.size(); i++) {
      assertEquals(foundTraders.get(i).getDob(), findAllTraders.get(i).getDob());
    }
    
    savedTrader.setEmail("solomon_c_blake@gmail.com");
    savedTrader = traderDao.save(savedTrader);
    assertNotEquals("solomoncblake@gmail.com", savedTrader.getEmail());
    
    traderDao.deleteById(savedTrader.getId());
    
    traderDao.deleteAll();
    
    assertEquals(traderDao.count(), 0);
  }
}