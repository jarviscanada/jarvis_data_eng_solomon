package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {
  @Captor
  ArgumentCaptor<SecurityOrder> captorSecurityOrder;
  @InjectMocks
  private OrderService orderService;
  @Mock
  private AccountDao accountDao;
  @Mock
  private SecurityOrderDao orderDao;
  @Mock
  private QuoteDao quoteDao;
  @Mock
  private PositionDao positionDao;
  @Mock
  private MarketOrderDto orderDto;
  @Mock
  private Quote quote;
  @Mock
  private Position position;
  @Mock
  private Account account;
  
  @Before
  public void setUp () {
    orderService = new OrderService(accountDao, orderDao, quoteDao, positionDao);
    
    when(quote.getTicker()).thenReturn("AAPL");
    when(quote.getAskPrice()).thenReturn(10.9);
    when(quote.getAskSize()).thenReturn(BigInteger.valueOf(10));
    when(quote.getBidPrice()).thenReturn(10.8);
    when(quote.getBidSize()).thenReturn(BigInteger.valueOf(10));
    when(quote.getLastPrice()).thenReturn(11.);
    
    when(quoteDao.findById(anyString())).thenReturn(Optional.of(quote));
    
    when(account.getId()).thenReturn(1);
    when(account.getAmount()).thenReturn(12000.);
    when(account.getTraderId()).thenReturn(1);
    
    when(accountDao.updateAmountById(any(Account.class), anyDouble())).thenReturn(account);
    when(accountDao.findById(anyInt())).thenReturn(Optional.of(account));
    
    when(orderDto.getAccountId()).thenReturn(1);
    when(orderDto.getOrderSize()).thenReturn(10);
    when(orderDto.getTicker()).thenReturn("AAPL");
  
    when(position.getPosition()).thenReturn(20);
    when(position.getTicker()).thenReturn("AAPL");
    when(position.getId()).thenReturn(1);
  
    when(positionDao.findByAccountId(anyInt())).thenReturn(Collections.singletonList(position));
  }
  
  @Test
  public void executeMarketBuyOrder () {
    when(orderDto.isBuyOrder()).thenReturn(true);
    orderService.executeMarketOrder(orderDto);
    verify(orderDao).save(captorSecurityOrder.capture());
    assertEquals("FILLED", captorSecurityOrder.getValue().getStatus());
  }

  @Test
  public void executeMarketSellOrder () {
    when(orderDto.isBuyOrder()).thenReturn(false);
    orderService.executeMarketOrder(orderDto);
    verify(orderDao).save(captorSecurityOrder.capture());
    assertEquals(quote.getAskPrice(), captorSecurityOrder.getValue().getPrice());
  }
}