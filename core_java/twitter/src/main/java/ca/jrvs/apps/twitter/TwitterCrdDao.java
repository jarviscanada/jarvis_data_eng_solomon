package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class TwitterCrdDao implements CrdDao<Tweet, String> {
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show/";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";
  
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";
  
  private static final int HTTP_OK = 200;
  
  private HttpHelper httpHelper;
  
  public TwitterCrdDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }
  
  @Override
  public Tweet create(Tweet entity) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    Tweet formedTweet;
    URI createPostUri;
    
    try {
      createPostUri =
          new URI((createUriString(POST_PATH, entity, null)
                       .append(percentEscaper.escape(entity.getText()))).toString());
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Unable to construct URI from provided entity.");
    }
    
    HttpResponse httpResponse = httpHelper.httpPost(createPostUri);
  
    try {
      formedTweet = createObjectFromHttpResponse(httpResponse, Tweet.class);
    } catch (Exception e) {
      throw new RuntimeException("Unable to create Tweet object from HttpResponse");
    }
    
    return formedTweet;
  }
  
  @Override
  public Tweet findById(String idString) {
    Tweet tweetToBeFound;
    URI findByIdRequest;
    
    try {
      findByIdRequest =
          new URI(createUriString(SHOW_PATH, null, idString).toString());
    } catch (Exception e) {
      throw new RuntimeException("Unable to create valid URI from provided idString." + idString);
    }
    
    HttpResponse httpResponse = httpHelper.httpGet(findByIdRequest);
  
    try {
      tweetToBeFound = createObjectFromHttpResponse(httpResponse, Tweet.class);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to create Tweet object from HttpResponse");
    }
    return tweetToBeFound;
  }
  
  @Override
  public Tweet deleteById(String idString) {
    Tweet tweetToBeDeleted;
    URI deleteByIdRequest = null;
    
    try {
      deleteByIdRequest = new URI(createUriString(DELETE_PATH,null, idString).toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  
    HttpResponse httpResponse = httpHelper.httpPost(deleteByIdRequest);
  
    try {
      tweetToBeDeleted = createObjectFromHttpResponse(httpResponse, Tweet.class);
    } catch (Exception e) {
      throw new RuntimeException("Unable to create Tweet object from HttpResponse");
    }
    
    return tweetToBeDeleted;
  }
  
  private StringBuilder createUriString(String tweetOperation, Tweet tweet, String id) {
    if (!(tweetOperation.equals(POST_PATH)
              | tweetOperation.equals(SHOW_PATH)
              | tweetOperation.equals(DELETE_PATH)
      )
    ) {
      throw new IllegalArgumentException("Illegal tweetOperation provided: " + tweetOperation);
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(API_BASE_URI);
    
    if (tweet == null) {
      stringBuilder.append(tweetOperation + id + ".json");
    } else {
      stringBuilder.append(tweetOperation + QUERY_SYM + "status" + EQUAL);
    }
    
    return stringBuilder;
  }

  private <T>  T createObjectFromHttpResponse(HttpResponse httpResponse,
                                              Class<T> objectClass) throws Exception {
    if (httpResponse == null) {
      throw new IllegalArgumentException("HttpResponse must not be null");
    }
    if (httpResponse.getStatusLine().getStatusCode() != HTTP_OK) {
      throw new HttpException("Http response returned with status code "
                                  + httpResponse.getStatusLine().getStatusCode());
    }
    String entityAsJsonString = EntityUtils.toString(httpResponse.getEntity());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return ((T) objectMapper.readValue(entityAsJsonString, objectClass));
  }
  
}
