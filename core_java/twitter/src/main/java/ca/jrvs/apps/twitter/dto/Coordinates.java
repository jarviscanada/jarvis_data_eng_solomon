package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({
    "coordinates",
    "type",
})

public class Coordinates {
  @JsonProperty("coordinates")
  private List<Float> lonLat;
  @JsonProperty("type")
  private String type;
  
  @JsonCreator
  public Coordinates(){}

  @JsonProperty("coordinates")
  public List<Float> getLonLat () {
    return lonLat;
  }
  @JsonProperty("coordinates")
  public void setLonLat (List<Float> lonLat) {
    this.lonLat = lonLat;
  }
  @JsonProperty("type")
  public String getType () {
    return type;
  }
  @JsonProperty("type")
  public void setType (String type) {
    this.type = type;
  }
  
}
