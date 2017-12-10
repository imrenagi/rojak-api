package id.rojak.election.domain.model.candidate;

import id.rojak.common.domain.model.IdentifiedDomainObject;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.domain.model.election.ElectionId;

import javax.persistence.*;

/**
 * Created by inagi on 7/3/17.
 */
@Entity
@Table(name="tbl_candidates")
public class Candidate extends IdentifiedDomainObject {

    @Embedded
    private CandidateId candidateId;

    @Embedded
    private ElectionId electionId;

    @Column(name="candidate_number")
    private int candidateNumber;

    @Column(name="tag_line")
    private String tagLine;

    @ManyToOne
    @JoinColumn(name="main_nominee_id", referencedColumnName = "id")
    private Nominee mainCandidate;

    @ManyToOne
    @JoinColumn(name="vice_nominee_id", referencedColumnName = "id")
    private Nominee viceCandidate;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="election_id_id", referencedColumnName = "id")
    private Election election;

    @Embedded
    private SocialMediaInformation socialMediaInformation;

    protected Candidate() {
        super();
    }

    public Candidate(
            CandidateId aCandidateId,
            ElectionId electionId,
            int candidateNumber,
            Nominee mainCandidate,
            Nominee viceCandidate,
            SocialMediaInformation socialMediaInformation) {
        this();

        this.assertArgumentFalse(mainCandidate.equals(viceCandidate),
                "Main Nominee and Vice Nominee must be different nominee");

        this.setCandidateId(aCandidateId);
        this.setElectionId(electionId);
        this.setCandidateNumber(candidateNumber);
        this.setMainCandidate(mainCandidate);
        this.setViceCandidate(viceCandidate);
        this.setSocialMediaInformation(socialMediaInformation);

        //TODO publish domain event if necessary
    }

    public void changeMainCandidate(Nominee aNewMainCandidate) {
        this.setMainCandidate(aNewMainCandidate);

        //TODO publish domain event if needed
    }

    public void changeViceCandidate(Nominee aNewViceCandidate) {
        this.setViceCandidate(aNewViceCandidate);;

        //TODO publish domain event if needed
    }

    public void cancelCandidacyInElection(Election election, CandidateService aCandidateService) {

        //TODO check candidate electionID sama atau gak.

//        this.assertArgumentNotNull(aGroup, "Group must not be null.");
//        this.assertArgumentEquals(this.tenantId(), aGroup.tenantId(), "Wrong tenant for this group.");
//        this.assertArgumentFalse(aGroupMemberService.isMemberGroup(aGroup, this.toGroupMember()), "Group recurrsion.");
//
//        if (this.groupMembers().add(aGroup.toGroupMember()) && !this.isInternalGroup()) {
//            DomainEventPublisher
//                    .instance()
//                    .publish(new GroupGroupAdded(
//                            this.tenantId(),
//                            this.name(),
//                            aGroup.name()));
//        }

        //TODO publish domain event
    }

    public void participateInElection(ElectionId electionId, CandidateService aCandidateService) {

        //TODO publish domain event
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObject = false;

        if (anObject != null && anObject.getClass() == this.getClass()) {
            Candidate typedObject = (Candidate) anObject;
            equalObject = typedObject.candidateId().equals(this.candidateId());
        }

        return equalObject;
    }

    public void setCandidateId(CandidateId candidateId) {
        this.assertArgumentNotNull(candidateId,
                "Candidate Id is required");
        this.assertArgumentLength(candidateId.id(), 36,
                "Candidate id may not exceed 36 characters");

        this.candidateId = candidateId;
    }

    public void setElectionId(ElectionId electionId) {
        this.assertArgumentNotNull(electionId,
                "Election Id is required");
        this.assertArgumentLength(electionId.id(), 36,
                "Election id may not exceed 36 characters");

        this.electionId = electionId;
    }

    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public void setTagLine(String tagLine) {
        this.assertArgumentNotNull(tagLine,
                "Tagline is required");
        this.assertArgumentLength(tagLine, 0, 255,
                "Tagline must not exceed 255 characters");

        this.tagLine = tagLine;
    }

    public void setMainCandidate(Nominee mainCandidate) {
        this.assertArgumentNotNull(mainCandidate,
                "Main candidate is required");

        this.mainCandidate = mainCandidate;
    }

    public void setViceCandidate(Nominee viceCandidate) {
        this.assertArgumentNotNull(viceCandidate,
                "Vice Candidate is required.");

        this.viceCandidate = viceCandidate;
    }

    public void setSocialMediaInformation(SocialMediaInformation socialMediaInformation) {
        this.assertArgumentNotNull(socialMediaInformation,
                "Social Media Information is required");

        this.socialMediaInformation = socialMediaInformation;
    }

    public void setImageUrl(String imageUrl) {
        this.assertArgumentNotEmpty(imageUrl, "Image URL should not be empty");

        this.imageUrl = imageUrl;
    }

    public void setElection(Election election) {
        this.assertArgumentNotNull(election, "Election is required");

        this.election = election;
    }

    public CandidateId candidateId() { return this.candidateId; }

    public ElectionId electionId() { return this.electionId; }

    public int candidateNumber() { return this.candidateNumber; }

    public String tagLine() { return this.tagLine; }

    public Nominee mainCandidate() { return this.mainCandidate; }

    public Nominee viceCandidate() { return this.viceCandidate; }

    public SocialMediaInformation socialMediaInformation() {
        return this.socialMediaInformation;
    }

    public String imageuRL() { return this.imageUrl; }

    public Election election() { return this.election; }

}
