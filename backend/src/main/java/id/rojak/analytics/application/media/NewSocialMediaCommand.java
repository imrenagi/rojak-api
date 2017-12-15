package id.rojak.analytics.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewSocialMediaCommand {

    private String facebookUrl;
    private String twitterId;
    private String instagramId;

    protected NewSocialMediaCommand() {

    }

    public NewSocialMediaCommand(String facebookUrl,
                                 String twitterId,
                                 String instagramId) {
        this();
        this.facebookUrl = facebookUrl;
        this.twitterId = twitterId;
        this.instagramId = instagramId;
    }

    @JsonProperty("facebook_url")
    public String getFacebookUrl() {
        return facebookUrl;
    }

    @JsonProperty("facebook_url")
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    @JsonProperty("twitter_id")
    public String getTwitterId() {
        return twitterId;
    }

    @JsonProperty("twitter_id")
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @JsonProperty("instagram_id")
    public String getInstagramId() {
        return instagramId;
    }

    @JsonProperty("instagram_id")
    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

}
