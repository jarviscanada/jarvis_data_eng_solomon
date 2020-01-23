package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "ca.jrvs.apps.twitter")
public class TwitterCliComponentScan {
  public static void main(String[] args) throws Exception {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(TwitterCliComponentScan.class);
    TwitterCliApp cliApp = context.getBean(TwitterCliApp.class);
    cliApp.run(args);
  }
}
