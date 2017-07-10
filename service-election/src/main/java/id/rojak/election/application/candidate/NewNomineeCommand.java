package id.rojak.election.application.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/9/17.
 */
public class NewNomineeCommand {

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    private String imageUrl;
    @NotNull
    private String nickName;
    private String webUrl;
    private String twitterId;
    private String instagramId;
    private String facebookUrl;

    public NewNomineeCommand(String firstName,
                             String middleName,
                             String lastName,
                             String imageUrl,
                             String nickName,
                             String webUrl,
                             String twitterId,
                             String instagramId,
                             String facebookUrl) {
        this(firstName, lastName, nickName);

        this.middleName = middleName;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
        this.twitterId = twitterId;
        this.instagramId = instagramId;
        this.facebookUrl = facebookUrl;
    }

    public NewNomineeCommand(String firstName,
                             String lastName,
                             String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    protected NewNomineeCommand() {}


    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("middle_name")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("nick_name")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("twitter_id")
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @JsonProperty("instagram_id")
    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    @JsonProperty("facebook_url")
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }
}
