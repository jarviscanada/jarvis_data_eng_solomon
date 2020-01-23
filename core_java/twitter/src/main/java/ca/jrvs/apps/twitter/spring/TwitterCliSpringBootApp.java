package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCliApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")
public class TwitterCliSpringBootApp implements CommandLineRunner {
  @Inject
  private static TwitterCliApp twitterCliApp;
  private static Logger logger = LoggerFactory.getLogger(TwitterCliSpringBootApp.class);
  
  @Autowired
  public TwitterCliSpringBootApp (TwitterCliApp twitterCliApp) {
    this.twitterCliApp = twitterCliApp;
  }
  
  /**
   * Parses incoming arguments for tweet operation.
   * Passes operation arguments to the appropriate Controller method.
   * @param args
  **/
  public void run(String... args) throws Exception {
    twitterCliApp.run(args);
  }

  /**Takes user input and forwards it to the run method
   * assuming the arguments are applicable.
   * Performs dependency injection on the TwitterController
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(TwitterCliSpringBootApp.class);
    
    application.setWebApplicationType(WebApplicationType.NONE);
    application.run(args);
  }
}
