package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//@Configuration
//@Component
public class TwitterCliBean {
  public static void main(String[] args) throws Exception {
    ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCliBean.class);
    TwitterCliApp twitterCliApp = context.getBean(TwitterCliApp.class);
    twitterCliApp.run(args);
  }
  
  @Bean
  public TwitterCliApp twitterCliApp(Controller controller) {
    return new TwitterCliApp(controller);
  }

  @Bean
  public TwitterController twitterController(Service service) {
    return new TwitterController(service);
  }
  
  @Bean
  public TwitterService twitterService(CrdDao crdDao) {
    return new TwitterService(crdDao);
  }
  
  @Bean
  public TwitterCrdDao twitterCrdDao(HttpHelper httpHelper) {
    return new TwitterCrdDao(httpHelper);
  }
  
  @Bean
  public static HttpHelper createAuthHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    if (consumerKey.isEmpty() | consumerSecret.isEmpty()
            | accessToken.isEmpty() | tokenSecret.isEmpty()) {
      throw new IllegalArgumentException("Authentication keys found in this environment are not "
                                             + "valid");
    }
    return new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
  }
  
}
