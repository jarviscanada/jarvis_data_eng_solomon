package ca.jrvs.apps.trading.model.domain;

import java.math.BigInteger;

public class Quote implements Entity<String> {

  private String ticker;
  private Double lastPrice;
  private Double bidPrice;
  private BigInteger bidSize;
  private Double askPrice;
  private BigInteger askSize;
  
  @Override
  public String getId () {
    return ticker;
  }
  
  @Override
  public void setID (String s) {
    this.ticker = s;
  }

  public String getTicker () {
    return ticker;
  }
  
  public void setTicker (String ticker) {
    this.ticker = ticker;
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
  
  public BigInteger getBidSize () {
    return bidSize;
  }
  
  public void setBidSize (BigInteger bidSize) {
    this.bidSize = bidSize;
  }
  
  public Double getAskPrice () {
    return askPrice;
  }
  
  public void setAskPrice (Double askPrice) {
    this.askPrice = askPrice;
  }
  
  public BigInteger getAskSize () {
    return askSize;
  }
  
  public void setAskSize (BigInteger askSize) {
    this.askSize = askSize;
  }
}