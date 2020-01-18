package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
  @InjectMocks
  TwitterController twitterController;
  @Mock
  TwitterService twitterService;
  
  Tweet tweet = new Tweet();
  Tweet tweet2 = new Tweet();
  final float lon = 89f;
  final float lat = -45f;
  final String text = "This is a test string.";
  final String idString = "1208114830516850688";
  
  @Before
  public void setUp() throws Exception {
    twitterController = new TwitterController(twitterService);

    tweet.setText(text);
    tweet.setCoordinates(new Coordinates());
    tweet.getCoordinates().setLonLat(Arrays.asList(lon, lat));
    tweet.setIdString(idString);
    tweet.setFavouriteCount(5);
    tweet.setRetweetCount(1);
    
    tweet2.setText(text + System.currentTimeMillis());
    tweet2.setCoordinates(new Coordinates());
    tweet2.getCoordinates().setLonLat(Arrays.asList(lon, lat));
    tweet2.setIdString(idString.replaceFirst("8", "0"));
    tweet.setFavouriteCount(3);
    tweet.setRetweetCount(8);

    doReturn((Arrays.asList(tweet, tweet2))).when(twitterService).deleteTweets(any());
    doReturn(tweet).when(twitterService).showTweet(any(), any());
  }
  
  @Test
  public void postTweet() {
    String[] args = {"TwitterCliApp", "post", tweet.getText(), lon + ":" + lat};
    try {
      System.out.println(TwitterUtils.toJsonFromObject(twitterController.postTweet(args)));
    } catch(JsonProcessingException e) {
      fail();
    }
  }
  
  @Test
  public void showTweet() {
    String[] args = {"TwitterCliApp", "show", tweet.getIdString(), "idString, text, retweetCount"};
  
    try {
      System.out.println(TwitterUtils.toJsonFromObject(twitterController.showTweet(args)));
    } catch (JsonProcessingException e) {
      fail();
    }
    assertTrue(true);
  }
  
  @Test
  public void deleteTweet() {
    String[] args = {"TwitterCliApp", "delete", tweet.getIdString(), tweet2.getIdString()};
    try {
      System.out.println(TwitterUtils.toJsonFromObject(twitterController.deleteTweet(args)));
    } catch (JsonProcessingException e) {
      fail();
    }
    assertTrue(true);
  }
}