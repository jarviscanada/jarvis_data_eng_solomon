package ca.jrvs.apps.trading.util;

public class TradingAppUtils {
  public static String verifyTicker (String s) {
    if (s.matches("^[a-zA-Z]{2,4}$") == false){
      throw new IllegalArgumentException("Ticker must have [2, 4] characters between [a, z], case" +
                                             "insensitive");
    }
    return s.toUpperCase();
  }
}
