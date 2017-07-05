package id.rojak.election.domain.model.election;

import id.rojak.election.common.domain.model.DomainEventPublisher;
import id.rojak.election.common.domain.model.IdentifiedDomainObject;

import id.rojak.election.domain.model.candidate.Candidate;


import javax.persistence.*;

import java.util.Date;

/**
 * Created by inagi on 7/3/17.
 */
//@Entity
//@Table(name="elections")
public class Election extends IdentifiedDomainObject {

//    @Embedded
    private ElectionId electionId;

    @Column(name="name")
    private String name;

//    @Embedded
    private ElectionDate electionDate;
//    @Enumerated(EnumType.STRING)
//    @Column(name="type")
    private ElectionType type;

//    @Column(name="city_id")
    private Integer cityId = -1;

//    @Column(name="province_id")
    private Integer provinceId = -1;

//    private Set<Candidate> candidates;

    public Election(
            ElectionId electionId,
            String name,
            Date electionDate,
            Date electionCampaignStart,
            Date electionCampaignEnd,
            ElectionType type) {
        super();

        this.setName(name);
        this.setElectionDate(new ElectionDate(
                electionDate,
                electionCampaignStart,
                electionCampaignEnd
            ));
        this.setType(type);

        //TODO handle other variable initialization

        //TODO publish event about new electio n creation
        DomainEventPublisher.instance()
                .publish(new ElectionCreated());
    }

    public Candidate addCandidate() {
        //TODO implement later
        return null;
    }

    public void removeCandidate() {
        //TODO implement later
        //remove candidate and call domain event
    }

    public ElectionDate electionDates() {
        return this.electionDate;
    }

    public ElectionType type() {
        return this.type;
    }

    public String name() {
        return this.name;
    }

    public ElectionId electionId() {
        return this.electionId;
    }

    public void setElectionId(ElectionId anElectionId) {
        this.assertArgumentNotNull(anElectionId, "ElectionDTO Id must not be null");
        this.assertArgumentNotEmpty(anElectionId.id(), "ElectionDTO Id must not be empty");

        this.electionId = anElectionId;
    }

    public void setName(String name) {
        this.assertArgumentNotEmpty(name, "ElectionDTO name must not be empty");

        this.name = name;
    }

    public void setElectionDate(ElectionDate electionDate) {
        this.assertArgumentNotNull(electionDate, "ElectionDTO Dates must not be null");

        this.electionDate = electionDate;
    }

    public void setType(ElectionType type) {
        this.assertArgumentNotNull(type, "ElectionDTO type must no be empty");

        this.type = type;
    }

}
