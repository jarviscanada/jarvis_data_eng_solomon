package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.MediaEntity;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.UrlEntity;

import java.util.List;

@JsonPropertyOrder({
    "hashtags",
    "user_mentions",
})
@JsonIgnoreProperties({"symbols", "urls", "mentions", "media", "polls"})
public class Entities extends org.springframework.social.twitter.api.Entities {
  @JsonProperty("urls")
  private List<UrlEntity> urls;
  @JsonProperty("hashtags")
  private List<HashTag> hashTags;
  @JsonProperty("user_mentions")
  private List<UserMention> userMentions;
  @JsonProperty("media")
  private List<MediaEntity> media;
  @JsonCreator
  public Entities(@JsonProperty("urls") List<UrlEntity> urls,
                  @JsonProperty("hashtags") List<HashTagEntity> tags,
                  @JsonProperty("user_mentions")  List<MentionEntity> mentions,
                  @JsonProperty("media") List<MediaEntity> media) {
    super(urls, tags, mentions, media);
  }
  @JsonProperty("hashtags")
  public void setHashTags(List<HashTag> hashTags) {
    this.hashTags = hashTags;
  }
  @JsonProperty("user_mentions")
  public List<UserMention> getUserMentions() {
    return userMentions;
  }
  @JsonProperty("user_mentions")
  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}
