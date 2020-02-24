package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.service.QuoteService;
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
  
}
