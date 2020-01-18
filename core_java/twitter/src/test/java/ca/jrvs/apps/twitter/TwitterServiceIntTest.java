package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Entities;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {
  TwitterService twitterService;
  TwitterCrdDao twitterCrdDao;
  TwitterHttpHelper twitterHttpHelper;
  Tweet realTweet, outboundTweet;
  Coordinates coordinates;
  List<Float> lonLat;
  
  @Before
  public void setUp () throws Exception {
    twitterHttpHelper = TwitterUtils.createAuthHelper();
    twitterCrdDao = new TwitterCrdDao(twitterHttpHelper);
    twitterService = new TwitterService(twitterCrdDao);
    
    realTweet = twitterCrdDao.createObjectFromHttpResponse(
        twitterHttpHelper.executeSignedHttpRequest(HttpMethod.GET,
            new URI("https://api.twitter.com/1.1/statuses/user_timeline.json"
                                + "?screen_name=scblake5&count=1")), Tweet.class);
    
    coordinates = new Coordinates();
    lonLat = new ArrayList<>();
    lonLat.add(89f);
    lonLat.add(-45f);
    coordinates.setLonLat(lonLat);
    outboundTweet = new Tweet(
        new Date().toString(),
        90071992540740993L,
        "90071992540740993",
        "Twitter Service test " + System.currentTimeMillis(),
        new Entities(null, null, null, null),
        coordinates,
        0,
        0,
        false,
        false
    );
  }
  
  @Test(expected = RuntimeException.class)
  public void integrationTest () throws JsonProcessingException {
    Tweet postedTweet = twitterService.postTweet(outboundTweet);
    
    assertNotNull(twitterService.showTweet(postedTweet.getIdString(), new String[]{"text", "idString",
        "createdAt"}));
    
    Tweet newOutboundTweet = new Tweet();
    newOutboundTweet.setId(realTweet.getId());
    newOutboundTweet.setIdString(newOutboundTweet.getId() + "");
    newOutboundTweet.setText("Twitter Service test 2 " + System.currentTimeMillis());
    Tweet newPostedTweet =  twitterService.postTweet(newOutboundTweet);
    
   assertFalse(twitterService.deleteTweets(new String[]{postedTweet.getIdString(),
        newPostedTweet.getIdString()}).isEmpty());
  
    assertNotNull(TwitterUtils.toJsonFromObject(twitterService.showTweet(newPostedTweet.getIdString(),
        new String[]{
        "text", "idString",
        "createdAt"})));
  
    fail();
  }
}