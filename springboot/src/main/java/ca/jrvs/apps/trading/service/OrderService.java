package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
  
  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
  
  private SecurityOrderDao orderDao;
  private AccountDao accountDao;
  private PositionDao positionDao;
  private QuoteDao quoteDao;
  
  @Autowired
  public OrderService (AccountDao accountDao, SecurityOrderDao orderDao, QuoteDao quoteDao,
                       PositionDao positionDao) {
    this.accountDao = accountDao;
    this.orderDao = orderDao;
    this.quoteDao = quoteDao;
    this.positionDao = positionDao;
  }
  
  public SecurityOrder executeMarketOrder (MarketOrderDto marketOrderDto) {
    SecurityOrder order = new SecurityOrder();
    
    order.setAccountId(marketOrderDto.getAccountId());
    order.setSize(marketOrderDto.getOrderSize());
    order.setTicker(marketOrderDto.getTicker());
    
    if (marketOrderDto.isBuyOrder()) {
      handleBuyMarketOrder(marketOrderDto, order,
          accountDao.findById(marketOrderDto.getAccountId())
              .orElseThrow(() -> new IllegalArgumentException("No such account")));
    } else {
      handleSellMarketOrder(marketOrderDto, order,
          accountDao.findById(marketOrderDto.getAccountId())
              .orElseThrow(() -> new IllegalArgumentException("No such account")));
    }
    
    return orderDao.save(order);
  }

  protected void handleBuyMarketOrder (MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
                                    Account account) {
    Quote quote = quoteDao.findById(marketOrderDto.getTicker())
                          .orElseThrow(
                              () -> new IllegalArgumentException("Unable to find '"
                                                                     + marketOrderDto.getTicker()
                                                                     + "'"));
    if (account.getAmount() >= marketOrderDto.getOrderSize()
                * quote.getBidPrice()) {
      accountDao.updateAmountById(account, account.getAmount()
              - marketOrderDto.getOrderSize() * quote.getBidPrice());
      securityOrder.setPrice(quote.getBidPrice());
      securityOrder.setAccountId(account.getId());
    } else {
      throw new IllegalArgumentException("Account balance must be greater than or equal to "
                                             + "cost of order.");
    }
    
    securityOrder.setStatus("FILLED");
  }
  
  protected void handleSellMarketOrder (MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
                                        Account account) {
    Position matchedPosition =
        positionDao.findByAccountId(account.getId()).stream().filter(
            position -> position.getTicker().equals(marketOrderDto.getTicker()))
            .collect(Collectors.toList()).get(0);
  
    Quote quote = quoteDao.findById(marketOrderDto.getTicker())
                      .orElseThrow(
                          () -> new IllegalArgumentException("Unable to find '"
                                                                 + marketOrderDto.getTicker()
                                                                 + "'"));
    if (marketOrderDto.getOrderSize() <= matchedPosition.getPosition()) {
      accountDao.updateAmountById(account,
          account.getAmount() + marketOrderDto.getOrderSize() * quote.getAskPrice());
      securityOrder.setPrice(quote.getAskPrice());
      securityOrder.setAccountId(account.getId());
    } else {
      throw new IllegalArgumentException("No positions exists for this order.");
    }
  
    securityOrder.setStatus("OPEN");
  }
  
}
