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
   * @param Ticker symbol for IEX Quote
   * @return IexQuote object with the specified ticker
   */
  public IexQuote findIexQuoteByTicker (String ticker) {
    return marketDataDao.findById(ticker).orElseThrow(
        ()-> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Pulls most recent quote data from IEX Cloud and updates stored
   * Quotes with the new information
   */
  public void updateMarketData() {
    quoteDao.findAll().forEach((quote) -> {
        buildQuoteFromIexQuote(marketDataDao.findById(quote.getId()).orElseThrow(
            () -> new IllegalArgumentException(quote.getId() + " cannot be found")
        ));
    });
  }

  /**
   * @param IexQuote object
   * @return Quote object built with information from the IexQuote
   */
  protected static Quote buildQuoteFromIexQuote (IexQuote iexQuote) {
    Quote builtQuote = new Quote();
    
    builtQuote.setID(iexQuote.getSymbol());
    builtQuote.setAskPrice(iexQuote.getIexAskPrice());
    builtQuote.setAskSize(iexQuote.getIexAskSize());
    builtQuote.setBidPrice(iexQuote.getIexBidPrice());
    builtQuote.setBidSize(iexQuote.getIexBidSize());
    
    return builtQuote;
  }

  /**
   * @param IexQuote object that should be saved
   */
  public void saveQuote (IexQuote iexQuote) {
    Quote quote = buildQuoteFromIexQuote(iexQuote);
    quoteDao.save(quote);
  }

  /**
   ** @param A list of IexQuote objects that should be saved
   */
  public void saveQuotes (List<IexQuote> iexQuotes) {
    List<Quote> quotes = new ArrayList<>();
    iexQuotes.forEach(iexQuote -> quotes.add(buildQuoteFromIexQuote(iexQuote)));
    quoteDao.saveAll(quotes);
  }

  /**
   * @return A list of Quote objects returned by querying a database
   */
  public List<Quote> findAllQuotes() {
    return (List<Quote>) quoteDao.findAll();
  }
}
