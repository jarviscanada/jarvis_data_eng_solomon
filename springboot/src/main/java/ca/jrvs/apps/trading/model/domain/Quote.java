package ca.jrvs.apps.trading.model.domain;

public class Quote implements Entity<String> {

  private String ticker;
  private Double lastPrice;
  private Double bidPrice;
  private Integer bidSize;
  private Double askPrices;
  private Integer askSize;
  
  @Override
  public String getId () {
    return ticker;
  }
  
  @Override
  public void setID (String s) {
    this.ticker = s;
  }

  public Double getLastPrice () {
    return lastPrice;
  }

  public void setLastPrice (Double lastPrice) {
    this.lastPrice = lastPrice;
  }
  
  public Double getBidPrice () {
    return bidPrice;
  }
  
  public void setBidPrice (Double bidPrice) {
    this.bidPrice = bidPrice;
  }
  
  public Integer getBidSize () {
    return bidSize;
  }
  
  public void setBidSize (Integer bidSize) {
    this.bidSize = bidSize;
  }
  
  public Double getAskPrices () {
    return askPrices;
  }
  
  public void setAskPrices (Double askPrices) {
    this.askPrices = askPrices;
  }
  
  public Integer getAskSize () {
    return askSize;
  }
  
  public void setAskSize (Integer askSize) {
    this.askSize = askSize;
  }
}