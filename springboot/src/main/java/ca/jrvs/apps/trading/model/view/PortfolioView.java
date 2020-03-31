package ca.jrvs.apps.trading.model.view;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;

public class PortfolioView {
  
  private Account account;
  private Position position;
  
  @Autowired
  public PortfolioView (Account account, Position position) {
    this.account = account;
    this.position = position;
  }
  
  public Account getAccount () {
    return account;
  }
  
  public void setAccount (Account account) {
    this.account = account;
  }
  
  public Position getPosition () {
    return position;
  }
  
  public void setPosition (Position position) {
    this.position = position;
  }
}
