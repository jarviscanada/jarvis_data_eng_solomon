package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.social.twitter.api.HashTagEntity;

@JsonPropertyOrder({
    "indices",
    "text",
})
public class HashTag extends HashTagEntity {
  @JsonProperty("indices")
  private int[] indices;
  @JsonProperty("text")
  private String text;
  
  @JsonCreator
  public HashTag(@JsonProperty("text") String text,
                  @JsonProperty("indices") int[] indices) {
    super(text, indices);
  }
  @JsonProperty("indices")
  public int[] getIndices () {
      return indices;
    }
  @JsonProperty("indices")
  public void setIndices (int[] indices) {
    this.indices = indices;
  }
  @JsonProperty("text")
  public String getText () {
    return text;
  }
  @JsonProperty("text")
  public void setText (String text) {
    this.text = text;
  }
}
