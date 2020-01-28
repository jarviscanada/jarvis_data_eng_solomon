package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.IexQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
  
  public IexQuote findIexQuoteByTicker (String ticker) {
    return null;
  }
  
}
