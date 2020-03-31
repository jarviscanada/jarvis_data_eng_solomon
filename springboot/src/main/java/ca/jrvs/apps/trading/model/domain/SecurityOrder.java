package ca.jrvs.apps.trading.model.domain;

public class SecurityOrder implements Entity<Integer> {
  
  private Integer id = 0;
  private Integer accountId = 0;
  private String status = null;
  private String ticker = null;
  private Integer size = 0;
  private Double price = 0.0;
  private String notes = null;

  @Override
  public Integer getId () {
    return id;
  }
  
  @Override
  public void setID (Integer integer) {
    this.id = integer;
  }

  public Integer getAccountId () {
    return accountId;
  }
  
  public void setAccountId (Integer accountId) {
    this.accountId = accountId;
  }
  
  public String getStatus () {
    return status;
  }
  
  public void setStatus (String status) {
    this.status = status;
  }
  
  public String getTicker () {
    return ticker;
  }
  
  public void setTicker (String ticker) {
    this.ticker = ticker;
  }
  
  public Integer getSize () {
    return size;
  }
  
  public void setSize (Integer size) {
    this.size = size;
  }
  
  public Double getPrice () {
    return price;
  }
  
  public void setPrice (Double price) {
    this.price = price;
  }
  
  public String getNotes () {
    return notes;
  }
  
  public void setNotes (String notes) {
    this.notes = notes;
  }
}
