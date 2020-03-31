package ca.jrvs.apps.trading.model.domain;

public class MarketOrderDto {
  
  private String ticker;
  private Integer orderSize;
  private boolean buyOrder;
  private Integer accountId;

  public MarketOrderDto (String ticker, Integer orderSize, boolean buyOrder, Integer accountId) {
    this.ticker = ticker;
    this.orderSize = orderSize;
    this.buyOrder = buyOrder;
    this.accountId = accountId;
  }

  public String getTicker () {
    return ticker;
  }
  
  public Integer getOrderSize () {
    return orderSize;
  }
  
  public boolean isBuyOrder () {
    return buyOrder;
  }
  
  public Integer getAccountId () {
    return accountId;
  }
}
