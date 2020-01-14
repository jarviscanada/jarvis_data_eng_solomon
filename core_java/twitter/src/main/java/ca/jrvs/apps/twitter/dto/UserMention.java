package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.social.twitter.api.MentionEntity;

@JsonPropertyOrder({
    "id",
    "id_str",
    "indices",
    "name",
    "screen_name",
})

public class UserMention extends MentionEntity {
  @JsonProperty("id")
  private long mentionedUserId;
  @JsonProperty("id_str")
  private String idString;
  @JsonProperty("indices")
  private int[] indices;
  @JsonProperty("name")
  private String name;
  @JsonProperty("screen_name")
  private String screenName;
  
  @JsonCreator
  public UserMention(@JsonProperty("id") long id,
                     @JsonProperty("screen_name") String screenName,
                     @JsonProperty("name") String name,
                     @JsonProperty("indices") int[] indices) {
    super(id, screenName, name, indices);
  }
  @JsonProperty("id")
  public long getMentionedUserId() {
      return mentionedUserId;
    }
  @JsonProperty("id")
  public void setMentionedUserId(long mentionedUserId) {
    this.mentionedUserId = mentionedUserId;
  }
  @JsonProperty("id_str")
  public String getIdString() {
    return idString;
  }
  @JsonProperty("id_str")
  public void setIdString(String idString) {
    this.idString = idString;
  }
  @JsonProperty("indices")
  public int[] getIndices() {
    return indices;
  }
  @JsonProperty("indices")
  public void setIndices(int[] indices) {
    this.indices = indices;
  }
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }
  @JsonProperty("screen_name")
  public String getScreenName() {
    return screenName;
  }
  @JsonProperty("screen_name")
  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }
}
