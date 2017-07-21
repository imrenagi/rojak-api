package id.rojak.analytics.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewMediaCommand {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String webUrl;

    private String mobileWebUrl;
    private String logoUrl;
    private NewSocialMediaCommand socialMedia;
    private NewPostalAddressCommand postalAddress;

    public NewMediaCommand() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
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

    @JsonProperty("social_media")
    public NewSocialMediaCommand getSocialMedia() {
        return socialMedia;
    }

    @JsonProperty("social_media")
    public void setSocialMedia(NewSocialMediaCommand socialMedia) {
        this.socialMedia = socialMedia;
    }

    @JsonProperty("postal_address")
    public NewPostalAddressCommand getPostalAddress() {
        return postalAddress;
    }

    @JsonProperty("postal_address")
    public void setPostalAddress(NewPostalAddressCommand postalAddress) {
        this.postalAddress = postalAddress;
    }
}
