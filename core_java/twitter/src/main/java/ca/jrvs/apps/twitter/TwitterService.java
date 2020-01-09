package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.pow;

public class TwitterService implements Service {
  @Inject
  TwitterCrdDao twitterCrdDao;
  
  public TwitterService(TwitterCrdDao twitterCrdDao) {
    this.twitterCrdDao = twitterCrdDao;
  }
  
  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    validateTweetParameters(tweet.getIdString(), tweet);
    return twitterCrdDao.create(tweet);
  }
  
  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    Tweet shownTweet = new Tweet();
    //List of all fields within the Tweet class (createdAt, id_str, etc.)
    List<Field> tweetFields = Arrays.stream(Tweet.class.getDeclaredFields())
                                  .collect(Collectors.toList());
    validateTweetParameters(id, null);
    
    Tweet foundTweet = twitterCrdDao.findById(id);
    
    //Find fields within Tweet objects, search field list for said field, if not there, set to null
    //else, set to foundTweet's value
    Arrays.stream(fields).forEach(
        (String tweetField) -> {
          Field field = null;
            try {
              if (!tweetFields.contains(tweetField)) {
                field = Tweet.class.getField(tweetField);
                field.set(shownTweet, null);
              } else {
                field.set(shownTweet, field.get(foundTweet));
              }
            } catch (NoSuchFieldException | IllegalAccessException e) {
              throw new IllegalArgumentException("Field \"" + tweetField + "\" is not part of the"
                                                     + " Tweet class.");
            }
        }
    );
    return shownTweet;
  }
  
  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> deletedTweets = new ArrayList<>();
    Arrays.stream(ids).forEach(
        (String string) -> {
          validateTweetParameters(string, null);
          deletedTweets.add(twitterCrdDao.deleteById(string));
        }
    );
    return deletedTweets;
  }
  
  public void validateTweetParameters(String id, Tweet tweet) {
    if (Long.parseLong(id) < ((long) pow(2.0, 53))
            || Long.parseLong(id) > (long) pow(2.0, 64)
            || id.length() < 1
            || id == null) {
      throw new IllegalArgumentException("INVALID TWEET: Tweet IDs are at minimum 53-bit "
                                             + "numbers, and at maximum "
                                             + "64-bits long.");
    }
    if (tweet != null) {
      if (abs(tweet.getCoordinates().getLonLat().get(0)) > 90) {
        throw new IllegalArgumentException("INVALID TWEET: Longitude values must be within the "
                                               + "inclusive range [-90, 90] to be valid.");
      }
      if (abs(tweet.getCoordinates().getLonLat().get(1)) > 180) {
        throw new IllegalArgumentException("INVALID TWEET: Longitude values must be within the "
                                               + "inclusive range [-180, 180] to be valid.");
      }
    }
  }
}
