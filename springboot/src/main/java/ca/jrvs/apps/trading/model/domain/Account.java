package ca.jrvs.apps.trading.model.domain;

public class Account implements Entity<Integer> {

  private Integer id = 0;
  private Integer traderId = 0;
  private Double amount = 0.0;
  
  @Override
  public Integer getId () {
    return id;
  }
  
  @Override
  public void setID (Integer integer) {
    this.id = integer;
  }

  public Integer getTraderId () {
    return traderId;
  }
  
  public void setTraderId (Integer traderId) {
    this.traderId = traderId;
  }
  
  public Double getAmount () {
    return amount;
  }
  
  public void setAmount (Double amount) {
    this.amount = amount;
  }
  
}
