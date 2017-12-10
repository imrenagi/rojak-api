package id.rojak.election.application.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/9/17.
 */
public class NewNomineeCommand {

    @NotNull
    private String id;
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

    public NewNomineeCommand(
            String id,
            String firstName,
            String middleName,
            String lastName,
            String imageUrl,
            String nickName,
            String webUrl,
            String twitterId,
            String instagramId,
            String facebookUrl) {
        this(id, firstName, lastName, nickName);

        this.middleName = middleName;
        this.imageUrl = imageUrl;
        this.webUrl = webUrl;
        this.twitterId = twitterId;
        this.instagramId = instagramId;
        this.facebookUrl = facebookUrl;
    }

    protected NewNomineeCommand(
            String id,
            String firstName,
            String lastName,
            String nickName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    protected NewNomineeCommand() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middle_name")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    @JsonProperty("nick_name")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getTwitterId() {
        return twitterId;
    }

    @JsonProperty("twitter_id")
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getInstagramId() {
        return instagramId;
    }

    @JsonProperty("instagram_id")
    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    @JsonProperty("facebook_url")
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }
}
