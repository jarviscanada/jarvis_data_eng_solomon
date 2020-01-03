package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterCrdDaoUnitTest {
  String tempTweetId;
  String sampleTweetId;
  String consumerKey;
  String consumerSecret;
  String accessToken;
  String tokenSecret;
  TwitterHttpHelper twitterHttpHelper;
  TwitterCrdDao twitterCrdDao;
  Tweet foundTweet, createdTweet;
  
  @Before
  public void setUp() throws Exception {
    sampleTweetId = "1213168212751323136"; //Replace with new tweet id from my timeline for testing
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    twitterCrdDao = new TwitterCrdDao(twitterHttpHelper);
    tempTweetId = sampleTweetId;
  }
  
  @After
  public void tearDown() throws Exception {
    consumerKey = null;
    consumerSecret = null;
    accessToken = null;
    tokenSecret = null;
    twitterHttpHelper = null;
    twitterCrdDao = null;
  }

  @Test
  public void aFindById() {
    foundTweet = twitterCrdDao.findById(sampleTweetId);
    assertTrue(foundTweet.getCreatedAt().getTime() < System.currentTimeMillis());
  }
  
  @Test
  public void bCreate() {
    foundTweet = twitterCrdDao.findById(sampleTweetId);
    String expectedTweetBody = "httpPost test @ t:";
  
    foundTweet.setText(foundTweet.getText().substring(0, expectedTweetBody.length())
                          + " " + System.currentTimeMillis());
    createdTweet = twitterCrdDao.create(foundTweet);
    assertNotEquals(foundTweet.getIdString(), createdTweet.getIdString());
  }
  
  @Test(expected = RuntimeException.class)
  public void cDeleteById() {
    Tweet deletedTweet = twitterCrdDao.deleteById(sampleTweetId);
    deletedTweet = twitterCrdDao.findById(deletedTweet.getIdString());
  }
}