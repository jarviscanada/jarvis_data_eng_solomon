package ca.jrvs.apps.trading.model.domain;

import java.math.BigInteger;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "companyName",
    "primaryExchange",
    "sector",
    "calculationPrice",
    "open",
    "openTime",
    "close",
    "closeTime",
    "high",
    "low",
    "latestPrice",
    "latestSource",
    "latestTime",
    "latestUpdate",
    "latestVolume",
    "iexRealtimePrice",
    "iexRealtimeSize",
    "iexLastUpdated",
    "delayedPrice",
    "delayedPriceTime",
    "extendedPrice",
    "extendedChange",
    "extendedChangePercent",
    "extendedPriceTime",
    "previousClose",
    "previousVolume",
    "change",
    "changePercent",
    "volume",
    "iexMarketPercent",
    "iexVolume",
    "avgTotalVolume",
    "iexBidPrice",
    "iexBidSize",
    "iexAskPrice",
    "iexAskSize",
    "marketCap",
    "peRatio",
    "week52High",
    "week52Low",
    "ytdChange",
    "lastTradeTime",
    "isUSMarketOpen"
})
public class IexQuote {
  
  @JsonProperty("symbol")
  private String symbol;
  @JsonProperty("companyName")
  private String companyName;
  @JsonProperty("primaryExchange")
  private String primaryExchange;
  @JsonProperty("sector")
  private String sector;
  @JsonProperty("calculationPrice")
  private String calculationPrice;
  @JsonProperty("open")
  private Double open;
  @JsonProperty("openTime")
  private BigInteger openTime;
  @JsonProperty("close")
  private Double close;
  @JsonProperty("closeTime")
  private BigInteger closeTime;
  @JsonProperty("high")
  private Double high;
  @JsonProperty("low")
  private Double low;
  @JsonProperty("latestPrice")
  private Double latestPrice;
  @JsonProperty("latestSource")
  private String latestSource;
  @JsonProperty("latestTime")
  private String latestTime;
  @JsonProperty("latestUpdate")
  private BigInteger latestUpdate;
  @JsonProperty("latestVolume")
  private BigInteger latestVolume;
  @JsonProperty("volume")
  private BigInteger volume;
  @JsonProperty("iexRealtimePrice")
  private Double iexRealtimePrice;
  @JsonProperty("iexRealtimeSize")
  private BigInteger iexRealtimeSize;
  @JsonProperty("iexLastUpdated")
  private BigInteger iexLastUpdated;
  @JsonProperty("delayedPrice")
  private Double delayedPrice;
  @JsonProperty("delayedPriceTime")
  private BigInteger delayedPriceTime;
  @JsonProperty("extendedPrice")
  private Double extendedPrice;
  @JsonProperty("extendedChange")
  private Double extendedChange;
  @JsonProperty("extendedChangePercent")
  private Double extendedChangePercent;
  @JsonProperty("extendedPriceTime")
  private BigInteger extendedPriceTime;
  @JsonProperty("previousClose")
  private Double previousClose;
  @JsonProperty("previousVolume")
  private BigInteger previousVolume;
  @JsonProperty("change")
  private Double change;
  @JsonProperty("changePercent")
  private Double changePercent;
  @JsonProperty("iexMarketPercent")
  private Double iexMarketPercent;
  @JsonProperty("iexVolume")
  private BigInteger iexVolume;
  @JsonProperty("avgTotalVolume")
  private BigInteger avgTotalVolume;
  @JsonProperty("iexBidPrice")
  private Double iexBidPrice;
  @JsonProperty("iexBidSize")
  private BigInteger iexBidSize;
  @JsonProperty("iexAskPrice")
  private Double iexAskPrice;
  @JsonProperty("iexAskSize")
  private BigInteger iexAskSize;
  @JsonProperty("marketCap")
  private BigInteger marketCap;
  @JsonProperty("week52High")
  private Double week52High;
  @JsonProperty("week52Low")
  private Double week52Low;
  @JsonProperty("ytdChange")
  private Double ytdChange;
  @JsonProperty("peRatio")
  private Double peRatio;
  @JsonProperty("lastTradeTime")
  private BigInteger lastTradeTime;
  @JsonProperty("isUSMarketOpen")
  private Boolean isUSMarketOpen;
//
//  public IexQuote ( @JsonProperty("symbol") String symbol,
//                    @JsonProperty("companyName") String companyName,
//                    @JsonProperty("calculationPrice") String calculationPrice,
//                    @JsonProperty("open") BigInteger open,
//                    @JsonProperty("openTime") BigInteger openTime,
//                    @JsonProperty("close") Double close,
//                    @JsonProperty("closeTime") BigInteger closeTime,
//                    @JsonProperty("high") Double high,
//                    @JsonProperty("low") Double low,
//                    @JsonProperty("latestPrice") Double latestPrice,
//                    @JsonProperty("latestSource") String latestSource,
//                    @JsonProperty("latestTime") String latestTime,
//                    @JsonProperty("latestUpdate") BigInteger latestUpdate,
//                    @JsonProperty("latestVolume") BigInteger latestVolume,
//                    @JsonProperty("volume") BigInteger volume,
//                    @JsonProperty("iexRealtimePrice") Double iexRealtimePrice,
//                    @JsonProperty("iexRealtimeSize") BigInteger iexRealtimeSize,
//                    @JsonProperty("iexLastUpdated") BigInteger iexLastUpdated,
//                    @JsonProperty("delayedPrice") Double delayedPrice,
//                    @JsonProperty("delayedPriceTime") BigInteger delayedPriceTime,
//                    @JsonProperty("extendedPrice") Double extendedPrice,
//                    @JsonProperty("extendedChange") Double extendedChange,
//                    @JsonProperty("extendedChangePercent") Double extendedChangePercent,
//                    @JsonProperty("extendedPriceTime") BigInteger extendedPriceTime,
//                    @JsonProperty("previousClose") Double previousClose,
//                    @JsonProperty("previousVolume") BigInteger previousVolume,
//                    @JsonProperty("change") Double change,
//                    @JsonProperty("changePercent") Double changePercent,
//                    @JsonProperty("iexMarketPercent") Double iexMarketPercent,
//                    @JsonProperty("iexVolume") BigInteger iexVolume,
//                    @JsonProperty("avgTotalVolume") BigInteger avgTotalVolume,
//                    @JsonProperty("iexBidPrice") Double iexBidPrice,
//                    @JsonProperty("iexBidSize") BigInteger iexBidSize,
//                    @JsonProperty("iexAskPrice") Double iexAskPrice,
//                    @JsonProperty("iexAskSize") BigInteger iexAskSize,
//                    @JsonProperty("marketCap") BigInteger marketCap,
//                    @JsonProperty("week52High") Double week52High,
//                    @JsonProperty("week52Low") Double week52Low,
//                    @JsonProperty("ytdChange") Double ytdChange,
//                    @JsonProperty("peRatio") Double peRatio,
//                    @JsonProperty("lastTradeTime") BigInteger lastTradeTime,
//                    @JsonProperty("isUSMarketOpen") Boolean isUSMarketOpen) {
//    this.setSymbol(symbol);
//    this.setCompanyName(companyName);
//    this.setCalculationPrice(calculationPrice);
//    this.setOpen(open);
//    this.setOpenTime(openTime);
//    this.setClose(close);
//    this.setCloseTime(closeTime);
//    this.setHigh(high);
//    this.setLow(low);
//    this.setLatestPrice(latestPrice);
//    this.setLatestSource(latestSource);
//    this.setLatestTime(latestTime);
//    this.setLatestUpdate(latestUpdate);
//    this.setLatestVolume(latestVolume);
//    this.setVolume(volume);
//    this.setIexRealtimePrice(iexRealtimePrice);
//    this.setIexRealtimeSize(iexRealtimeSize);
//    this.setIexLastUpdated(iexLastUpdated);
//    this.setDelayedPrice(delayedPrice);
//    this.setDelayedPriceTime(delayedPriceTime);
//    this.setExtendedPrice(extendedPrice);
//    this.setExtendedChange(extendedChange);
//    this.setExtendedChangePercent(extendedChangePercent);
//    this.setExtendedPriceTime(extendedPriceTime);
//    this.setPreviousClose(previousClose);
//    this.setPreviousVolume(previousVolume);
//    this.setChange(change);
//    this.setChangePercent(changePercent);
//    this.setIexMarketPercent(iexMarketPercent);
//    this.setIexVolume(iexVolume);
//    this.setAvgTotalVolume(avgTotalVolume);
//    this.setIexBidPrice(iexBidPrice);
//    this.setIexBidSize(iexBidSize);
//    this.setIexAskPrice(iexAskPrice);
//    this.setIexAskSize(iexAskSize);
//    this.setMarketCap(marketCap);
//    this.setWeek52High(week52High);
//    this.setWeek52Low(week52Low);
//    this.setYtdChange(ytdChange);
//    this.setPeRatio(peRatio);
//    this.setLastTradeTime(lastTradeTime);
//    this.setUSMarketOpen(isUSMarketOpen);
//  }

  @JsonProperty("symbol")
  public String getSymbol() {
    return symbol;
  }
  
  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
  
  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }
  
  @JsonProperty("companyName")
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @JsonProperty("primaryExchange")
  public String getPrimaryExchange() {
    return primaryExchange;
  }

  @JsonProperty("primaryExchange")
  public void setPrimaryExchange(String primaryExchange) {
    this.primaryExchange = primaryExchange;
  }

  @JsonProperty("sector")
  public String getSector() {
    return sector;
  }

  @JsonProperty("sector")
  public void setSector(String sector) {
    this.sector = sector;
  }

  @JsonProperty("calculationPrice")
  public String getCalculationPrice() {
    return calculationPrice;
  }
  
  @JsonProperty("calculationPrice")
  public void setCalculationPrice(String calculationPrice) {
    this.calculationPrice = calculationPrice;
  }
  
  @JsonProperty("open")
  public Double getOpen() {
    return open;
  }
  
  @JsonProperty("open")
  public void setOpen(Double open) {
    this.open = open;
  }
  
  @JsonProperty("openTime")
  public BigInteger getOpenTime() {
    return openTime;
  }
  
  @JsonProperty("openTime")
  public void setOpenTime(BigInteger openTime) {
    this.openTime = openTime;
  }
  
  @JsonProperty("close")
  public Double getClose() {
    return close;
  }
  
  @JsonProperty("close")
  public void setClose(Double close) {
    this.close = close;
  }
  
  @JsonProperty("closeTime")
  public BigInteger getCloseTime() {
    return closeTime;
  }
  
  @JsonProperty("closeTime")
  public void setCloseTime(BigInteger closeTime) {
    this.closeTime = closeTime;
  }
  
  @JsonProperty("high")
  public Double getHigh() {
    return high;
  }
  
  @JsonProperty("high")
  public void setHigh(Double high) {
    this.high = high;
  }
  
  @JsonProperty("low")
  public Double getLow() {
    return low;
  }
  
  @JsonProperty("low")
  public void setLow(Double low) {
    this.low = low;
  }
  
  @JsonProperty("latestPrice")
  public Double getLatestPrice() {
    return latestPrice;
  }
  
  @JsonProperty("latestPrice")
  public void setLatestPrice(Double latestPrice) {
    this.latestPrice = latestPrice;
  }
  
  @JsonProperty("latestSource")
  public String getLatestSource() {
    return latestSource;
  }
  
  @JsonProperty("latestSource")
  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }
  
  @JsonProperty("latestTime")
  public String getLatestTime() {
    return latestTime;
  }
  
  @JsonProperty("latestTime")
  public void setLatestTime(String latestTime) {
    this.latestTime = latestTime;
  }
  
  @JsonProperty("latestUpdate")
  public BigInteger getLatestUpdate() {
    return latestUpdate;
  }
  
  @JsonProperty("latestUpdate")
  public void setLatestUpdate(BigInteger latestUpdate) {
    this.latestUpdate = latestUpdate;
  }
  
  @JsonProperty("latestVolume")
  public BigInteger getLatestVolume() {
    return latestVolume;
  }
  
  @JsonProperty("latestVolume")
  public void setLatestVolume(BigInteger latestVolume) {
    this.latestVolume = latestVolume;
  }
  
  @JsonProperty("volume")
  public BigInteger getVolume() {
    return volume;
  }
  
  @JsonProperty("volume")
  public void setVolume(BigInteger volume) {
    this.volume = volume;
  }
  
  @JsonProperty("iexRealtimePrice")
  public Double getIexRealtimePrice() {
    return iexRealtimePrice;
  }
  
  @JsonProperty("iexRealtimePrice")
  public void setIexRealtimePrice(Double iexRealtimePrice) {
    this.iexRealtimePrice = iexRealtimePrice;
  }
  
  @JsonProperty("iexRealtimeSize")
  public BigInteger getIexRealtimeSize() {
    return iexRealtimeSize;
  }
  
  @JsonProperty("iexRealtimeSize")
  public void setIexRealtimeSize(BigInteger iexRealtimeSize) {
    this.iexRealtimeSize = iexRealtimeSize;
  }
  
  @JsonProperty("iexLastUpdated")
  public BigInteger getIexLastUpdated() {
    return iexLastUpdated;
  }
  
  @JsonProperty("iexLastUpdated")
  public void setIexLastUpdated(BigInteger iexLastUpdated) {
    this.iexLastUpdated = iexLastUpdated;
  }
  
  @JsonProperty("delayedPrice")
  public Double getDelayedPrice() {
    return delayedPrice;
  }
  
  @JsonProperty("delayedPrice")
  public void setDelayedPrice(Double delayedPrice) {
    this.delayedPrice = delayedPrice;
  }
  
  @JsonProperty("delayedPriceTime")
  public BigInteger getDelayedPriceTime() {
    return delayedPriceTime;
  }
  
  @JsonProperty("delayedPriceTime")
  public void setDelayedPriceTime(BigInteger delayedPriceTime) {
    this.delayedPriceTime = delayedPriceTime;
  }
  
  @JsonProperty("extendedPrice")
  public Double getExtendedPrice() {
    return extendedPrice;
  }
  
  @JsonProperty("extendedPrice")
  public void setExtendedPrice(Double extendedPrice) {
    this.extendedPrice = extendedPrice;
  }
  
  @JsonProperty("extendedChange")
  public Double getExtendedChange() {
    return extendedChange;
  }
  
  @JsonProperty("extendedChange")
  public void setExtendedChange(Double extendedChange) {
    this.extendedChange = extendedChange;
  }
  
  @JsonProperty("extendedChangePercent")
  public Double getExtendedChangePercent() {
    return extendedChangePercent;
  }
  
  @JsonProperty("extendedChangePercent")
  public void setExtendedChangePercent(Double extendedChangePercent) {
    this.extendedChangePercent = extendedChangePercent;
  }
  
  @JsonProperty("extendedPriceTime")
  public BigInteger getExtendedPriceTime() {
    return extendedPriceTime;
  }
  
  @JsonProperty("extendedPriceTime")
  public void setExtendedPriceTime(BigInteger extendedPriceTime) {
    this.extendedPriceTime = extendedPriceTime;
  }
  
  @JsonProperty("previousClose")
  public Double getPreviousClose() {
    return previousClose;
  }
  
  @JsonProperty("previousClose")
  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
  }
  
  @JsonProperty("previousVolume")
  public BigInteger getPreviousVolume() {
    return previousVolume;
  }
  
  @JsonProperty("previousVolume")
  public void setPreviousVolume(BigInteger previousVolume) {
    this.previousVolume = previousVolume;
  }
  
  @JsonProperty("change")
  public Double getChange() {
    return change;
  }
  
  @JsonProperty("change")
  public void setChange(Double change) {
    this.change = change;
  }
  
  @JsonProperty("changePercent")
  public Double getChangePercent() {
    return changePercent;
  }
  
  @JsonProperty("changePercent")
  public void setChangePercent(Double changePercent) {
    this.changePercent = changePercent;
  }
  
  @JsonProperty("iexMarketPercent")
  public Double getIexMarketPercent() {
    return iexMarketPercent;
  }
  
  @JsonProperty("iexMarketPercent")
  public void setIexMarketPercent(Double iexMarketPercent) {
    this.iexMarketPercent = iexMarketPercent;
  }
  
  @JsonProperty("iexVolume")
  public BigInteger getIexVolume() {
    return iexVolume;
  }
  
  @JsonProperty("iexVolume")
  public void setIexVolume(BigInteger iexVolume) {
    this.iexVolume = iexVolume;
  }
  
  @JsonProperty("avgTotalVolume")
  public BigInteger getAvgTotalVolume() {
    return avgTotalVolume;
  }
  
  @JsonProperty("avgTotalVolume")
  public void setAvgTotalVolume(BigInteger avgTotalVolume) {
    this.avgTotalVolume = avgTotalVolume;
  }
  
  @JsonProperty("iexBidPrice")
  public Double getIexBidPrice() {
    return iexBidPrice;
  }
  
  @JsonProperty("iexBidPrice")
  public void setIexBidPrice(Double iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }
  
  @JsonProperty("iexBidSize")
  public BigInteger getIexBidSize() {
    return iexBidSize;
  }
  
  @JsonProperty("iexBidSize")
  public void setIexBidSize(BigInteger iexBidSize) {
    this.iexBidSize = iexBidSize;
  }
  
  @JsonProperty("iexAskPrice")
  public Double getIexAskPrice() {
    return iexAskPrice;
  }
  
  @JsonProperty("iexAskPrice")
  public void setIexAskPrice(Double iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }
  
  @JsonProperty("iexAskSize")
  public BigInteger getIexAskSize() {
    return iexAskSize;
  }
  
  @JsonProperty("iexAskSize")
  public void setIexAskSize(BigInteger iexAskSize) {
    this.iexAskSize = iexAskSize;
  }
  
  @JsonProperty("marketCap")
  public BigInteger getMarketCap() {
    return marketCap;
  }
  
  @JsonProperty("marketCap")
  public void setMarketCap(BigInteger marketCap) {
    this.marketCap = marketCap;
  }

  @JsonProperty("peRatio")
  public Double getPeRatio() {
    return peRatio;
  }

  @JsonProperty("peRatio")
  public void setPeRatio(Double peRatio) {
    this.peRatio = peRatio;
  }

  @JsonProperty("week52High")
  public Double getWeek52High() {
    return week52High;
  }
  
  @JsonProperty("week52High")
  public void setWeek52High(Double week52High) {
    this.week52High = week52High;
  }
  
  @JsonProperty("week52Low")
  public Double getWeek52Low() {
    return week52Low;
  }
  
  @JsonProperty("week52Low")
  public void setWeek52Low(Double week52Low) {
    this.week52Low = week52Low;
  }
  
  @JsonProperty("ytdChange")
  public Double getYtdChange() {
    return ytdChange;
  }
  
  @JsonProperty("ytdChange")
  public void setYtdChange(Double ytdChange) {
    this.ytdChange = ytdChange;
  }
  
  @JsonProperty("lastTradeTime")
  public BigInteger getLastTradeTime() {
    return lastTradeTime;
  }
  
  @JsonProperty("lastTradeTime")
  public void setLastTradeTime(BigInteger lastTradeTime) {
    this.lastTradeTime = lastTradeTime;
  }

  @JsonProperty("isUSMarketOpen")
  public Boolean getUSMarketOpen () {
    return isUSMarketOpen;
  }

  @JsonProperty("isUSMarketOpen")
  public void setUSMarketOpen (Boolean USMarketOpen) {
    isUSMarketOpen = USMarketOpen;
  }
}
