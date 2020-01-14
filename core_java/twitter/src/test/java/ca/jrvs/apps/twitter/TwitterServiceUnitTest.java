package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Entities;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @InjectMocks
  TwitterService twitterService;
  @Mock
  TwitterCrdDao twitterCrdDao;
  
  Tweet tweet, tweet2;
  Coordinates coordinates;
  String[] fields;
  List<String> ids;

  @Before
  public void setUp () throws Exception {
    twitterService = new TwitterService(twitterCrdDao);
    
    coordinates = new Coordinates();
    ArrayList<Float> lonLat = new ArrayList<Float>();
    lonLat.add(0, 89f);
    lonLat.add(1, -45f);
    coordinates.setLonLat(lonLat);
    tweet = new Tweet(
        new Date(),
        90071992540740993L,
        "90071992540740993",
        "this is the text body of a tweet",
        new Entities(null, null, null, null),
        coordinates,
        0,
        0,
        false,
        false
    );
    tweet2  = new Tweet();
    tweet2.setId(190071992540740993L);
    tweet2.setIdString("190071992540740993");
    tweet2.setText("this tweet is too long. this tweet is too long. this tweet is too long."
                     + " this tweet is too long. this tweet is too long. this tweet is too long.");
    tweet2.setCoordinates(tweet.getCoordinates());
    
    fields = new String[]{"id", "idString", "text"};
    ids = new ArrayList<String>();
    ids.add("90071992540740993");
    ids.add("190071992540740993");
  
    when(twitterCrdDao.create(tweet)).thenReturn(tweet);
    when(twitterCrdDao.findById("90071992540740993")).thenReturn(tweet);
    when(twitterCrdDao.deleteById("90071992540740993")).thenReturn(tweet);
    when(twitterCrdDao.deleteById("190071992540740993")).thenReturn(tweet2);
  }
  
  @After
  public void tearDown () throws Exception {
    twitterService = null;
  }
  
  @Test
  public void postTweet () {
    Tweet postedTweet = twitterService.postTweet(tweet);
    
    assertEquals(postedTweet.getIdString(), "90071992540740993");
    assertEquals(postedTweet.getText(), "this is the text body of a tweet");
  }
  
  @Test
  public void showTweet () {
    Tweet shownTweet = twitterService.showTweet(tweet.getIdString(), fields);
    
    assertEquals(shownTweet.getIdString(), tweet.getIdString());
    assertEquals(shownTweet.getId(), tweet.getId());
    assertEquals(shownTweet.getText(), tweet.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteTweets () {
    List<Tweet> tweetList =  twitterService.deleteTweets(ids.toArray(new String[2]));
    assertFalse(tweetList.isEmpty());
    
    tweetList.clear();

    ids.set(0, "213456789087643234576897654321");
    tweetList =  twitterService.deleteTweets(ids.toArray(new String[2])); //Should throw exception
    assertTrue(false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void validateTweetParameters() {
    try {
      tweet2.getCoordinates().getLonLat().set(0, -195f);
      twitterService.validateTweetParameters(tweet2.getIdString(), tweet2);
    } catch (IllegalArgumentException iAe) {
      assert(true);
      throw new IllegalArgumentException("test pass");
    }
    assertTrue(false);
  }
}