package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiTest {

  public static String CONSUMER_KEY = System.getenv("consumerKey");
  public static String CONSUMER_SECRET = System.getenv("consumerSecret");
  public static String ACCESS_TOKEN = System.getenv("accessToken");
  public static String TOKEN_SECRET = System.getenv("tokenSecret");

  public static void main (String[] args) throws Exception {
    //Setup oauth
    OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    oAuthConsumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
    
    //HTTP GET request
    String status = "This is my second tweet. This time using Java!"; //Tweet message
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpPost httpRequest = new HttpPost(
        "https://api.twitter.com/1.1/statuses/update.json?status="
            + percentEscaper.escape(status)
    );
    
    //Sign request and add headers
    oAuthConsumer.sign(httpRequest);
    
    System.out.println("HTTP Request Headers");
    Arrays.stream(httpRequest.getAllHeaders()).forEach(System.out::println);
    
    //finally, send the request
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse httpResponse = httpClient.execute(httpRequest);
    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
  }
}
