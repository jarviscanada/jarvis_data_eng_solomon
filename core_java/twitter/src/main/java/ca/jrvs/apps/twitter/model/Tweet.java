package ca.jrvs.apps.twitter.model;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Entities;
import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})
@JsonIgnoreProperties(ignoreUnknown = true)

public class Tweet {
  @JsonProperty("created_at")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss Z yyyy")
  private String createdAt = null;
  @JsonProperty("id")
  private long id = 0;
  @JsonProperty("id_str")
  private String idString = null;
  @JsonProperty("text")
  private String text = null;
  @JsonProperty("entities")
  private Entities entities = null;
  @JsonProperty("coordinates")
  private Coordinates coordinates = null;
  @JsonProperty("retweet_count")
  private int retweetCount = 0;
  @JsonProperty("favorite_count")
  private int favouriteCount = 0;
  @JsonProperty("favorited")
  private boolean favourited  = false;
  @JsonProperty("retweeted")
  private boolean retweeted = false;

  @JsonCreator
  public Tweet(@JsonProperty("created_at") String createdAt,
                @JsonProperty("id") long id,
                @JsonProperty("id_str") String idString,
                @JsonProperty("text") String text,
                @JsonProperty("entities") Entities entities,
                @JsonProperty("coordinates")  Coordinates coordinates,
                @JsonProperty("retweet_count") int retweetCount,
                @JsonProperty("favorite_count") int favouriteCount,
                @JsonProperty("favorited") boolean favourited,
                @JsonProperty("retweeted") boolean retweeted) {
    this.setCreatedAt(createdAt);
    this.setId(id);
    this.setIdString(idString);
    this.setText(text);
    this.setEntities(entities);
    this.setCoordinates(coordinates);
    this.setRetweetCount(retweetCount);
    this.setFavouriteCount(favouriteCount);
    this.setFavourited(favourited);
    this.setRetweeted(retweeted);
  }
  
  public Tweet() {
  }
  
  @JsonGetter("created_at")
  public String getCreatedAt() {
      return createdAt;
    }
  @JsonSetter("created_at")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  @JsonSetter("created_at")
  @JsonGetter("id")
  public long getId() {
    return id;
  }
  @JsonSetter("id")
  public void setId(long id) {
    this.id = id;
  }
  @JsonGetter("id_str")
  public String getIdString() {
    return idString;
  }
  @JsonSetter("id_str")
  public void setIdString(String idString) {
    this.idString = idString;
  }
  @JsonGetter("text")
  public String getText() {
    return text;
  }
  @JsonSetter("text")
  public void setText(String text) {
    this.text = text;
  }
  @JsonGetter("entities")
  public Entities getEntities() {
    return entities;
  }
  @JsonSetter("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
  }
  @JsonGetter("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }
  @JsonSetter("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }
  @JsonGetter("retweet_count")
  public int getRetweetCount() {
    return retweetCount;
  }
  @JsonSetter("retweet_count")
  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }
  @JsonGetter("favorite_count")
  public int getFavouriteCount() {
    return favouriteCount;
  }
  @JsonSetter("favorite_count")
  public void setFavouriteCount(int favouriteCount) {
    this.favouriteCount = favouriteCount;
  }
  @JsonGetter("favorited")
  public boolean isFavourited() {
    return favourited;
  }
  @JsonSetter("favorited")
  public void setFavourited(boolean favourited) {
    this.favourited = favourited;
  }
  @JsonGetter("retweeted")
  public boolean isRetweeted() {
    return retweeted;
  }
  @JsonSetter("retweeted")
  public void setRetweeted(boolean retweeted) {
    this.retweeted = retweeted;
  }
}
