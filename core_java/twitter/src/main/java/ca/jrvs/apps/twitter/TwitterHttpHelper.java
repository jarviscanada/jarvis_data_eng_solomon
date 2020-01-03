package ca.jrvs.apps.twitter;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {

  private OAuthConsumer consumer;
  private HttpClient httpClient;
  
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
                            String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = HttpClientBuilder.create().build();
  }
  
  @Override
  public HttpResponse httpPost(URI uri) {
    return executeSignedHttpRequest(HttpMethod.POST, uri);
  }
  
  @Override
  public HttpResponse httpGet(URI uri) {
    return executeSignedHttpRequest(HttpMethod.GET, uri);
  }
  
  public HttpResponse executeSignedHttpRequest(HttpMethod requestType, URI uri) {
    HttpUriRequest httpRequest;
    HttpResponse finalResponse = null;
    switch (requestType) {
      case GET:
        httpRequest = new HttpGet(uri);
        break;
      case POST:
        httpRequest = new HttpPost(uri);
        break;
      default:
        throw new IllegalArgumentException("Illegal HTTP method " + requestType + "provided.");
    }
    try {
      consumer.sign(httpRequest);
      finalResponse =  httpClient.execute(httpRequest);
    } catch (OAuthExpectationFailedException | OAuthMessageSignerException
                 | OAuthCommunicationException oauthEx) {
      System.err.println("Unable to properly authenticate request.");
    } catch (IOException ioEx) {
      System.err.println("Unable to properly perform request.");
    }
    return finalResponse;
  }
}
