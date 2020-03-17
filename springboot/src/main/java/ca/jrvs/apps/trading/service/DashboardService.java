package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.view.PortfolioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DashboardService {
  
  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;
  
  @Autowired
  public DashboardService (TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
                           QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create and return a TraderAccountView with the trader and account associated with the traderId
   * @param traderId
   * @throws IllegalArgumentException if traderId is invalid
   * @return TraderAccountView
   */
  public TraderAccountView getTraderAccount (Integer traderId) {
    Trader trader = traderDao.findById(traderId).orElseThrow(() -> new IllegalArgumentException(
        "Invalid traderId"));
    Account account =
        accountDao.findByTraderId(trader.getId()).orElseThrow(() -> new IllegalArgumentException(
            "Invalid traderId"));
    return new TraderAccountView(trader, account);
  }
  
  /**
   * Create and return a PortfolioView with the account and position associated with the traderId
   * @param traderId
   * @throws IllegalArgumentException If traderId is not valid
   * @return PortfolioView
   */
  public PortfolioView getProfileViewByTraderId (Integer traderId) {
    Account account =
        accountDao.findByTraderId(traderId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
    Position position = positionDao.findByAccountId(account.getId()).get(0);
    return new PortfolioView(account, position);
  }

  /**
   * @throws IllegalArgumentException if traderId is not valid
   */
  private Account findAccountByTraderId (Integer traderId) {
    return accountDao.findByTraderId(traderId).orElseThrow(() -> new IllegalArgumentException(
        "Invalid traderId"));
  }
  
}
