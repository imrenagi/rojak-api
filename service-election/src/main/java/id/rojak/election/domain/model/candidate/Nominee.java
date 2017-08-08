package id.rojak.election.domain.model.candidate;

import id.rojak.election.common.domain.model.IdentifiedDomainObject;
import id.rojak.election.domain.model.election.Election;

import javax.persistence.*;
import java.util.List;

/**
 * Created by imrenagi on 7/6/17.
 */
@Entity
@Table(name = "tbl_nominees")
public class Nominee extends IdentifiedDomainObject {

    @Embedded
    private FullName fullName;

    @Embedded
    private SocialMediaInformation socialMediaInformation;

    @Column(name = "nick_name")
    private String nickName;

    @Embedded
    private NomineeId nomineeId;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "mainCandidate", orphanRemoval = true)
    private List<Candidate> asMainCandidates;

    protected Nominee() {
        super();
    }

    public Nominee(
            NomineeId nomineeId,
            FullName aName,
            String aNickName,
            SocialMediaInformation aSocialMediaInformation) {
        super();
        this.setFullName(aName);
        this.setNickName(aNickName);
        this.setSocialMediaInformation(aSocialMediaInformation);
        this.setNomineeId(nomineeId);


    }

    public Nominee(NomineeId nomineeId,
                   FullName aName,
                   String aNickName,
                   String photoUrl,
                   SocialMediaInformation aSocialMediaInformation) {
        this(nomineeId, aName, aNickName, aSocialMediaInformation);
        this.setPhotoUrl(photoUrl);
    }

    public void installPhoto(String anPhotoUrl) {
        this.setPhotoUrl(anPhotoUrl);

        //TODO publish domain event if necessary
    }

    public void changeName(FullName aName) {
        this.setFullName(aName);

        //TODO publish domain event
    }

    public boolean isParticipatedIn(Election election) {

        boolean isParticipated = false;

        for(Candidate candidate : election.candidates()) {

            if (candidate.mainCandidate().equals(this)
                    || candidate.viceCandidate().equals(this)) {

                isParticipated = true;
                break;
            }
        }

        return isParticipated;

    }

    public void setPhotoUrl(String photoUrl) {
        this.assertArgumentNotEmpty(photoUrl, "PhotoURL is required");
        this.assertArgumentNotNull(photoUrl, "Photo URL must not be null");

        this.photoUrl = photoUrl;
    }

    public String photoUrl() {
        return this.photoUrl;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObject = false;

        if (anObject != null && anObject.getClass() == this.getClass()) {
            Nominee typedObject = (Nominee) anObject;
            equalObject = typedObject.nomineeId().equals(this.nomineeId());
        }

        return equalObject;
    }

    public void setNomineeId(NomineeId nomineeId) {
        this.assertArgumentNotNull(nomineeId, "Nominee Id is required");
        this.assertArgumentNotEmpty(nomineeId.id(), "Nominee id must not be empty");

        this.nomineeId = nomineeId;
    }

    public void setFullName(FullName fullName) {
        this.assertArgumentNotNull(fullName, "Full Name must not be null");

        this.fullName = fullName;
    }

    public void setSocialMediaInformation(SocialMediaInformation socialMediaInformation) {
        this.assertArgumentNotNull(socialMediaInformation, "Social Media Information must not be null");

        this.socialMediaInformation = socialMediaInformation;
    }

    public void setNickName(String nickName) {
        this.assertArgumentNotNull(nickName, "Nick Name must not be null");
        this.assertArgumentNotEmpty(nickName, "Nick Name must not be empty");
        this.assertArgumentLength(nickName, 1, 10, "Nick name length must be between 1 and 10");

        this.nickName = nickName;
    }

    public FullName fullName() {
        return this.fullName;
    }

    public SocialMediaInformation socialMediaInformation() {
        return this.socialMediaInformation;
    }

    public String nickName() {
        return this.nickName;
    }

    public NomineeId nomineeId() {
        return this.nomineeId;
    }

    public List<Candidate> asMainCandidate() {
        return this.asMainCandidates;
    }
}
