package ca.jrvs.apps.trading.model.domain;

public class Position implements Entity<Integer> {

  private Integer accountId  = 0;
  private String ticker = null;
  private Integer position = 0;
  
  @Override
  public Integer getId () {
    return accountId;
  }
  
  @Override
  public void setID (Integer integer) {
    this.accountId = integer;
  }

  public String getTicker () {
    return ticker;
  }
  
  public void setTicker (String ticker) {
    this.ticker = ticker;
  }
  
  public Integer getPosition () {
    return position;
  }
  
  public void setPosition (Integer position) {
    this.position = position;
  }
}
