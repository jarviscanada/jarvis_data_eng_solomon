package ca.jrvs.apps.trading.model;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;

public class TraderAccountView {
  private Trader trader;
  private Account account;
  
  @Autowired
  public TraderAccountView(Trader trader, Account account) {
    this.trader = trader;
    this.account = account;
  }

  public Trader getTrader () {
    return trader;
  }
  
  public Account getAccount () {
    return account;
  }
}
