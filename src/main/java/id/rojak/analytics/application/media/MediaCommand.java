package id.rojak.analytics.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 12/23/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaCommand {

    @NotNull
    private String name;

    @NotNull
    private String webUrl;

    @NotNull
    private String logoUrl;

    private String mobileWebUrl;
    private String facebookUrl;
    private String twitterId;
    private String instagramId;

    public MediaCommand() {
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("mobile_web_url")
    public String getMobileWebUrl() {
        return mobileWebUrl;
    }

    @JsonProperty("mobile_web_url")
    public void setMobileWebUrl(String mobileWebUrl) {
        this.mobileWebUrl = mobileWebUrl;
    }

    @JsonProperty("logo_url")
    public String getLogoUrl() {
        return logoUrl;
    }

    @JsonProperty("logo_url")
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
