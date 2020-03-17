package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.DashboardController;
import ca.jrvs.apps.trading.controller.OrderController;
import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.controller.TraderAccountController;
import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.service.DashboardService;
import ca.jrvs.apps.trading.service.OrderService;
import ca.jrvs.apps.trading.service.QuoteService;
import ca.jrvs.apps.trading.service.TraderAccountService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao", "ca.jrvs.apps.trading.service"})
public class AppConfig {
  private Logger logger = LoggerFactory.getLogger(AppConfig.class);
  
  @Bean
  public MarketDataConfig marketDataConfig() {
    MarketDataConfig dataConfig = new MarketDataConfig();
    dataConfig.setHost("https://cloud.iexapis.com/v1/");
    dataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
    return dataConfig;
  }

  @Bean
  public DataSource dataSource() {
    String url = System.getenv("PSQL_URL");
    String user = System.getenv("PSQL_USER");
    String password = System.getenv("PSQL_PASSWORD");
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);
    return basicDataSource;
  }
  
  @Bean
  public HttpClientConnectionManager httpClientConnectionManager() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(50);
    connectionManager.setDefaultMaxPerRoute(50);
    return connectionManager;
  }
  
  @Bean
  public QuoteController quoteController(QuoteService quoteService) {
    return new QuoteController(quoteService);
  }

  @Bean
  public QuoteService quoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    return new QuoteService(quoteDao, marketDataDao);
  }

 @Bean
  public QuoteDao quoteDao (DataSource dataSource) {
    return new QuoteDao(dataSource);
  }

  @Bean
  public MarketDataDao marketDataDao (HttpClientConnectionManager clientConnectionManager,
                                      MarketDataConfig config) {
    return new MarketDataDao(clientConnectionManager, config);
  }
  
  @Bean
  public TraderAccountController traderAccountController(TraderAccountService traderAccountService){
    return new TraderAccountController(traderAccountService);
  }
  
  @Bean
  public TraderAccountService traderAccountService(TraderDao traderDao, AccountDao accountDao,
        PositionDao positionDao, SecurityOrderDao securityOrderDao) {
    return new TraderAccountService(traderDao, accountDao, positionDao, securityOrderDao);
  }
  
  @Bean
  public TraderDao traderDao (DataSource dataSource) {
    return new TraderDao(dataSource);
  }

  @Bean
  public AccountDao accountDao (DataSource dataSource) {
    return new AccountDao(dataSource);
  }

  @Bean
  public PositionDao positionDao (DataSource dataSource) {
    return new PositionDao(dataSource);
  }

  @Bean
  public SecurityOrderDao securityOrderDao (DataSource dataSource) {
    return new SecurityOrderDao(dataSource);
  }
  
  @Bean
  public OrderController orderController (OrderService orderService) {
    return new OrderController(orderService);
  }

  @Bean
  public OrderService orderService (AccountDao accountDao, SecurityOrderDao orderDao, QuoteDao quoteDao,
                                    PositionDao positionDao) {
    return new OrderService(accountDao, orderDao, quoteDao, positionDao);
  }
  
  @Bean
  public DashboardService dashboardService (TraderDao traderDao, PositionDao positionDao,
                                            AccountDao accountDao, QuoteDao quoteDao) {
    return new DashboardService(traderDao, positionDao, accountDao, quoteDao);
  }
  
  @Bean
  public DashboardController dashboardController (DashboardService dashboardService) {
    return new DashboardController(dashboardService);
  }
  
}
