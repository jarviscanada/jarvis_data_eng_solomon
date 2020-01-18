package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    Tweet tweetToPost = new Tweet();
    tweetToPost.setText(args[2]);
    
    Coordinates coords = new Coordinates();
    String[] providedLatLon = args[3].split(":");
    coords.setLonLat(Arrays.stream(providedLatLon).map(Float::parseFloat).collect(Collectors.toList()));
    tweetToPost.setCoordinates(coords);
    
    return twitterService.postTweet(tweetToPost);
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
    List<String> fields = Arrays.asList(args[3].replace(" ","").split(","));
  
    Map<String, Boolean> matchedFields = new HashMap<>();
    
    List<String> declaredFieldNames =
        Arrays.stream(Tweet.class.getDeclaredFields())
            .map(Field::getName).collect(Collectors.toList());
  
    for (String field : fields) {
      String regexField = ".*" + field + "$";
      for (String name : declaredFieldNames) {
        if (name.matches(regexField)) {
          matchedFields.put(field, true);
        }
      }
    }
  
    if (matchedFields.containsValue(false)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Field(s): ");
      for (String key : matchedFields.keySet()) {
        if (!matchedFields.get(key)) {
          stringBuilder.append(key).append(", ");
        }
      }
      stringBuilder.replace(stringBuilder.length(), stringBuilder.length() + 1, " is(are)"
                                                             + " not present in the Tweet class.");
      throw new IllegalArgumentException(stringBuilder.toString());
    }

    return twitterService.showTweet(args[2], fields.toArray(new String[]{}));
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
    return twitterService.deleteTweets(Arrays.copyOfRange(args, 2, args.length));
  }

}
