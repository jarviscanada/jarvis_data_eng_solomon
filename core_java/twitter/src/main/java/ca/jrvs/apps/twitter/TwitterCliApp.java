package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class TwitterCliApp {
  @Inject
  private static TwitterController twitterController;
  private static Logger logger = LoggerFactory.getLogger(TwitterCliApp.class);
  
  /**
   * Parses incoming arguments for tweet operation.
   * Passes operation arguments to the appropriate Controller method.
   * @param args
  **/
  public static void run(String[] args) throws Exception {
    switch (args[1]) {
      case "post":
        logger.info("Created Tweet:");
        System.out.println(TwitterUtils.toJsonFromObject(twitterController.postTweet(args)));
        break;
      case "show":
        logger.info("Retrieved tweet:");
        String tweetToShowJson = TwitterUtils.toJsonFromObject(twitterController.showTweet(args));
        List<String> toShow = Arrays.asList(tweetToShowJson.split("\n"));
        StringBuilder tweetJson = new StringBuilder();
        for (String line : toShow) {
          toShow.stream().filter(
              (String tweetLine) -> tweetLine.contains(line)
                                        || tweetLine.matches("^[}{]$"))
              .forEach(tweetJson::append);
        }
        System.out.println(tweetJson.toString());
        break;
      case "delete":
        logger.info("Tweet(s) deleted:");
        List<Tweet>  deletedTweets = twitterController.deleteTweet(args);
        deletedTweets.forEach((Tweet tweet) -> {
          try {
            System.out.println(TwitterUtils.toJsonFromObject(tweet) + ",\n");
          } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse Tweet object");
          }
        });
        break;
      default: throw new IllegalArgumentException("[Usage] TwitterCliApp post|show|delete "
                                                      + "\"text\" longitude:latitude|postId");
    }
  }

  /**Takes user input and forwards it to the run method
   * assuming the arguments are applicable.
   * Performs dependency injection on the TwitterController
   * @param args
   */
  public static void main(String[] args) throws Exception {
    if (args.length < 3 || args[1].matches("[^a-z]+")) {
      throw new IllegalArgumentException("[Usage] TwitterCliApp post \"text\" "
                                            + "\"longitude:latitude\"\n"
                                            + "        TwitterCliApp show|delete \"postId(s)\"");
    }
    TwitterHttpHelper twitterHttpHelper = TwitterUtils.createAuthHelper();
    TwitterCrdDao twitterCrdDao = new TwitterCrdDao(twitterHttpHelper);
    TwitterService twitterService = new TwitterService(twitterCrdDao);
    twitterController = new TwitterController(twitterService);
    
    run(args);
  }
}
