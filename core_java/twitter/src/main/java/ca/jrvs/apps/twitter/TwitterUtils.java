package ca.jrvs.apps.twitter;

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
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    
      String entityAsJsonString = jsonString.substring(1, jsonString.length() - 1); //removing []
    
      return ((T) objectMapper.readValue(entityAsJsonString, clazz));
    }
}
