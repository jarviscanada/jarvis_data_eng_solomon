package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TwitterCrdDaoIntTest {
  String tempTweetId;
  String sampleTweetId;
  String consumerKey;
  String consumerSecret;
  String accessToken;
  String tokenSecret;
  TwitterHttpHelper twitterHttpHelper;
  TwitterCrdDao twitterCrdDao;
  
  @Before
  public void setUp () throws Exception {
    sampleTweetId = "1213125870149414919"; //Replace with new tweet id from my timeline for testing
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    twitterCrdDao = new TwitterCrdDao(twitterHttpHelper);
    tempTweetId = sampleTweetId;
  }
  
  @After
  public void tearDown () throws Exception {
    consumerKey = null;
    consumerSecret = null;
    accessToken = null;
    tokenSecret = null;
    twitterHttpHelper = null;
    twitterCrdDao = null;
  }

@Test
  public void integrationTest () {
    String expectedTweetBody = "httpPost test @ t:";
    
    Tweet realTweet = twitterCrdDao.findById(tempTweetId);
    realTweet.setText(realTweet.getText().substring(0, expectedTweetBody.length())
                          + " " + System.currentTimeMillis());
    Tweet testTweet = twitterCrdDao.create(realTweet);
    
    tempTweetId = realTweet.getIdString();

    assertEquals(testTweet.getText().substring(0, expectedTweetBody.length()),  //Ignores
        // millisecond addition, which is used to make the tweets unique
        expectedTweetBody);
    
    Tweet deletedTweet = twitterCrdDao.deleteById(tempTweetId);
    realTweet = twitterCrdDao.findById(sampleTweetId);
    
    assertNotEquals(deletedTweet.getText(), realTweet.getText());
  }
  
}