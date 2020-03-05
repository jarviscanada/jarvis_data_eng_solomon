package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    if (account.getAmount() >= marketOrderDto.getOrderSize()
                * quoteDao.findById(marketOrderDto.getTicker()).get().getAskPrice()) {
      accountDao.updateAmountById(account, account.getAmount()
              - marketOrderDto.getOrderSize()
                    * quoteDao.findById(marketOrderDto.getTicker()).get().getAskPrice());
      
      securityOrder.setPrice(quoteDao.findById(marketOrderDto.getTicker()).get().getAskPrice());
      securityOrder.setAccountId(account.getId());
    } else {
      throw new IllegalArgumentException("Account balance must be greater than or equal to "
                                             + "cost of order.");
    }
    
    securityOrder.setStatus("FILLED");
  }
  
  protected void handleSellMarketOrder (MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
                                        Account account) {
    List<Position> positionList = positionDao.findByAccountId(account.getId());
    
    if (positionList.size() > 0 && positionList.stream()
                   .anyMatch(position -> position.getTicker().equals(marketOrderDto.getTicker()))) {
      
    } else {
      throw new IllegalArgumentException("No positions exists for this order.");
    }
  
    securityOrder.setStatus("OPEN");
  }
  
}
