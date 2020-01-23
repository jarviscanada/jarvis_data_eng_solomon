package ca.jrvs.apps.twitter;

import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;


public class TwitterHttpHelperTest {
  String consumerKey;
  String consumerSecret;
  String accessToken;
  String tokenSecret;
  TwitterHttpHelper twitterHttpHelper;
  String uriString;
  PercentEscaper percentEscaper;
  URI uri;
  
  @Before
  public void setUp () throws Exception {
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    percentEscaper = new PercentEscaper("", false);
    uriString = "";
  }
  
  @After
  public void tearDown () throws Exception {
    consumerKey = null;
    consumerSecret = null;
    accessToken = null;
    tokenSecret = null;
    twitterHttpHelper = null;
    uri = null;
  }
  
  @Test
  public void httpPost () throws IOException {
    uriString = "https://api.twitter.com/1.1/statuses/update.json?status=";
    String status = "httpPost test @ t: " + System.currentTimeMillis() + "";
    uri = URI.create(uriString + percentEscaper.escape(status));
    HttpResponse httpResponse = twitterHttpHelper.httpPost(uri);
    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
  }
  
  @Test
  public void httpGet () throws IOException {
    uriString = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=scblake5";
    uri = URI.create(uriString);
    HttpResponse httpResponse = twitterHttpHelper.httpGet(uri);
    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
  }
}