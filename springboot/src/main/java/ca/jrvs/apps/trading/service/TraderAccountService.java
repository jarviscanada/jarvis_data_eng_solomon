package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.TraderAccountView;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {
  
  private TraderDao traderDao;
  private AccountDao accountDao;
  private PositionDao positionDao;
  private SecurityOrderDao securityOrderDao;

  @Autowired
  public TraderAccountService (TraderDao traderDao, AccountDao accountDao,
                               PositionDao positionDao, SecurityOrderDao securityOrderDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.securityOrderDao = securityOrderDao;
  }

  public TraderAccountView createTraderAndAccount (Trader trader) {
    return null;
  }
  
  public void deleteTraderById (Integer id) {
  }
  
  public Account deposit (Integer traderId, Double funds) {
    return null;
  }
  
  public Account withdraw (Integer traderId, Double funds) {
    return null;
  }
  
}
