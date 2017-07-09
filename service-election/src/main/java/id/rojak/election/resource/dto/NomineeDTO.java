package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.election.domain.model.candidate.Nominee;

/**
 * Created by imrenagi on 7/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NomineeDTO {

    private String fullName;
    private String nickName;
    private String twitterId;

    protected NomineeDTO() {

    }

    public NomineeDTO(Nominee nominee) {
        this(nominee.fullName().asFormattedName(),
                nominee.nickName(),
                nominee.socialMediaInformation().twitterId());
    }

    public NomineeDTO(String fullName,
                      String nickName,
                      String twitterId) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.twitterId = twitterId;
    }

    @JsonProperty("name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("nick_name")
    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JsonProperty("twitter_id")
    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }
}
