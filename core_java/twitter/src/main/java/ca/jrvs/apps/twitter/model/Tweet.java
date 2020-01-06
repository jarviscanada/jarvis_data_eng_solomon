package ca.jrvs.apps.twitter.model;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Entities;
import com.fasterxml.jackson.annotation.*;

import java.util.Date;

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
  private Date createdAt;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String idString;
  @JsonProperty("text")
  private String text;
  @JsonProperty("entities")
  private Entities entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private int retweetCount;
  @JsonProperty("favorite_count")
  private int favouriteCount;
  @JsonProperty("favorited")
  private boolean favourited;
  @JsonProperty("retweeted")
  private boolean retweeted;

  @JsonCreator
  public Tweet(@JsonProperty("created_at") Date createdAt,
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
  
  public Tweet(org.springframework.social.twitter.api.Tweet realTweet) {
    this.createdAt = realTweet.getCreatedAt();
    this.id = realTweet.getId();
    this.idString = realTweet.getIdStr();
    this.text = realTweet.getText();
    this.entities = (Entities) realTweet.getEntities();
    this.coordinates = new Coordinates();
    this.retweetCount = realTweet.getRetweetCount();
    this.favouriteCount = realTweet.getFavoriteCount();
    this.favourited = realTweet.isFavorited();
    this.retweeted = realTweet.isRetweeted();
  }
  
  @JsonGetter("created_at")
  public Date getCreatedAt() {
      return createdAt;
    }
  @JsonSetter("created_at")
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
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
