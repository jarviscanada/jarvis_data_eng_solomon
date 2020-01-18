package ca.jrvs.apps.twitter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class TwitterUtils {
  /**
   * Reads OAuth keys from the environment
   * and creates a new TwitterHttpHelper
   * @return Authenticated TwitterHttpHelper
   */
  public static TwitterHttpHelper createAuthHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    if (consumerKey.isEmpty() | consumerSecret.isEmpty()
            | accessToken.isEmpty() | tokenSecret.isEmpty()) {
      throw new IllegalArgumentException("Authentication keys found in this environment are not "
                                            + "valid");
    }
    return new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
  }

  /**
   * Takes httpEntity as json string as well as the class of represented class.
   * Determines if the string is a collection of objects
   * and return the appropriate casted object
   * @param jsonString
   * @param clazz
   * @param <T>
   * @return clazz casted Object represented by json string
   * @throws IOException
   */
  public static <T> T fromJsonToObject(String jsonString, Class clazz) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//    objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    if (jsonString.startsWith("[")) jsonString = jsonString.substring(1, jsonString.length() - 1);
  
    return (T) objectMapper.readValue(jsonString, clazz);
  }

  /**
   * Takes Object and returns a nice JSON string
   * @param object
   * @return Nicely formatted JSON string representing the object
   * @throws JsonProcessingException
   */
  public static String toJsonFromObject(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    return objectMapper.writeValueAsString(object);
  }
    
    
}
