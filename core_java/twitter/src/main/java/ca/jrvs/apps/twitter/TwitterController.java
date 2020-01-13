package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;

import javax.inject.Inject;
import java.util.List;

public class TwitterController implements Controller {
  @Inject
  TwitterService twitterService;
  
  public TwitterController(TwitterService twitterService) {
    this.twitterService = twitterService;
  }
  
  
  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {

    return null;
  }
  
  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    return null;
  }
  
  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    return null;
  }

}
