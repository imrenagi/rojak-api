package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.election.domain.model.candidate.Nominee;

/**
 * Created by imrenagi on 7/9/17.
 */
public class NomineeDTO {

    private String id;
    private String fistName;
    private String middleName;
    private String lastName;
    private String nickName;

    protected NomineeDTO() {

    }

    public NomineeDTO(Nominee nominee) {
        this(nominee.nomineeId().id(),
                nominee.fullName().firstName(),
                nominee.fullName().middleName(),
                nominee.fullName().lastName(),
                nominee.nickName());
    }


    public NomineeDTO(String id,
                      String fistName,
                      String middleName,
                      String lastName,
                      String nickName) {
        this.id = id;
        this.fistName = fistName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("first_name")
    public String getFistName() {
        return fistName;
    }

    @JsonProperty("middle_name")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("nick_name")
    public String getNickName() {
        return nickName;
    }
}
