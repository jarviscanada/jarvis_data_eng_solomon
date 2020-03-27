package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static ca.jrvs.apps.trading.util.TradingAppUtils.verifyTicker;

@Transactional
@Service
public class QuoteService {
  public static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
  
  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;
  
  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.marketDataDao = marketDataDao;
    this.quoteDao = quoteDao;
  }

  /**
   * @param iexQuote object
   * @return Quote object built with information from the IexQuote
   */
  protected static Quote buildQuoteFromIexQuote (IexQuote iexQuote) {
    Quote builtQuote = new Quote();
    
    builtQuote.setID(iexQuote.getSymbol());
    builtQuote.setLastPrice(iexQuote.getLatestPrice());
    builtQuote.setAskPrice(iexQuote.getIexAskPrice());
    builtQuote.setAskSize(iexQuote.getIexAskSize());
    builtQuote.setBidPrice(iexQuote.getIexBidPrice());
    builtQuote.setBidSize(iexQuote.getIexBidSize());
    
    return builtQuote;
  }

  /**
   * @param ticker symbol for IEX Quote
   * @return IexQuote object with the specified ticker
   */
  public IexQuote findIexQuoteByTicker (String ticker) {
    return marketDataDao.findById(verifyTicker(ticker)).orElseThrow(
        ()-> new IllegalArgumentException(ticker.toUpperCase() + " is invalid"));
  }

  /**
   * Pulls most recent quote data from IEX Cloud and updates stored
   * Quotes with the new information
   * @return
   */
  public List<Quote> updateMarketData() {
    List<Quote> updatedIexQuotes = new ArrayList<>();
    quoteDao.findAll().forEach((quote) -> {
      updatedIexQuotes.add(buildQuoteFromIexQuote(marketDataDao.findById(quote.getId()).orElseThrow(
            () -> new IllegalArgumentException(quote.getId() + " cannot be found")
        )));
    });
    return updatedIexQuotes;
  }

  /**
   * @param ticker of quote that should be saved
   * @return saved quote object
   */
  public Quote saveQuote (String ticker) {
    return quoteDao.save(buildQuoteFromIexQuote(findIexQuoteByTicker(verifyTicker(ticker))));
  }

  /**
   * @param quote that should be saved
   * @return saved quote object
   */
  public Quote saveQuote (Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   ** @param tickers for which their IexQuote should be saved
   */
  public List<Quote> saveQuotes (List<String> tickers) {
    List<Quote> quotes = new ArrayList<>();
    tickers.forEach(
        ticker -> quotes.add(buildQuoteFromIexQuote(marketDataDao.findById(verifyTicker(ticker))
                        .orElseThrow(
                            ()-> new IllegalArgumentException(
                                "Invalid ticker :" + ticker.toUpperCase())))));
    return (List<Quote>) quoteDao.saveAll(quotes);
  }

  /**
   * @return A list of Quote objects returned by querying a database
   */
  public List<Quote> findAllQuotes() {
    return (List<Quote>) quoteDao.findAll();
  }
}
