package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.pow;

public class TwitterService implements Service {
  @Inject
  CrdDao crdDao;
  
  public TwitterService(TwitterCrdDao crdDao) {
    this.crdDao = crdDao;
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
    return (Tweet) crdDao.create(tweet);
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
    validateTweetParameters(id, null);
    Tweet foundTweet = (Tweet) crdDao.findById(id);
  
    Tweet shownTweet = new Tweet();
    Map<String, Method> methodMap = new HashMap<>();
    Method setterMethod = null;
    Method getterMethod = null;
  
    Arrays.stream(Tweet.class.getDeclaredMethods())
        .forEach((Method method) -> methodMap.put(method.getName(), method));
    
    for (String providedField : fields) {
      String methodNamePattern = ".*"
                               + "["
                               + providedField.substring(0, 1)
                               + providedField.substring(0, 1).toUpperCase()
                               + "]"
                               + providedField.substring(1) + "$";
      
      for (String key : methodMap.keySet()) {
        if (key.matches(methodNamePattern)) {
          if (key.substring(0, 3).matches("set")) {
            setterMethod = methodMap.get(key);
          }
          if (key.substring(0, 3).matches("get")) {
            getterMethod = methodMap.get(key);
          }
          if (setterMethod != null && getterMethod != null) {
            try {
              setterMethod.invoke(shownTweet, getterMethod.invoke(foundTweet));
              setterMethod = null;
              getterMethod = null;
            } catch (IllegalArgumentException iAe) {
              throw new IllegalArgumentException(iAe);
            }catch(IllegalAccessException iAe2) {
              throw new IllegalArgumentException(iAe2);
            } catch (InvocationTargetException iAe) {
              throw new IllegalArgumentException(iAe);
            }
          }
        }
      }
    }
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
          deletedTweets.add((Tweet) crdDao.deleteById(string));
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
    if (tweet != null && tweet.getCoordinates() != null) {
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
