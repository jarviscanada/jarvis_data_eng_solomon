package ca.jrvs.apps.twitter;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import static org.junit.Assert.assertEquals;

public class TwitterCrdDaoIntTest {
  String consumerKey;
  String consumerSecret;
  String accessToken;
  String tokenSecret;
  TwitterHttpHelper twitterHttpHelper;
  TwitterCrdDao twitterCrdDao;
  Tweet realTweet;
  
  @Before
  public void setUp() throws Exception {
    twitterHttpHelper = TwitterUtils.createAuthHelper();
    twitterCrdDao = new TwitterCrdDao(twitterHttpHelper);
    realTweet = twitterCrdDao.createObjectFromHttpResponse(twitterHttpHelper.httpGet(
        new URI("https://api.twitter.com/1.1/statuses/" +
                    "user_timeline.json?screen_name=scblake5&count=1")), Tweet.class);
  }
  
  @After
  public void tearDown() throws Exception {
    consumerKey = null;
    consumerSecret = null;
    accessToken = null;
    tokenSecret = null;
    twitterHttpHelper = null;
    twitterCrdDao = null;
    realTweet = null;
  }

  @Test(expected = RuntimeException.class)
  public void integrationTest() {
    String expectedTweetBody = "httpPost test @ t:";
    
    realTweet.setText(realTweet.getText().substring(0, expectedTweetBody.length())
                          + " " + System.currentTimeMillis());
    Tweet testTweet = twitterCrdDao.create(realTweet);

    assertEquals(testTweet.getText().substring(0, expectedTweetBody.length()),  //Ignores
        // millisecond addition, which is used to make the tweets unique
        expectedTweetBody);
    
    Tweet deletedTweet = twitterCrdDao.deleteById(realTweet.getIdString());
    realTweet = twitterCrdDao.findById(realTweet.getIdString());
  }
}