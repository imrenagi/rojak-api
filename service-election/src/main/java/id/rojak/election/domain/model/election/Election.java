package id.rojak.election.domain.model.election;

import id.rojak.election.common.domain.model.DomainEventPublisher;
import id.rojak.election.common.domain.model.IdentifiedDomainObject;

import id.rojak.election.domain.model.candidate.Candidate;


import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by inagi on 7/3/17.
 */
@Entity
@Table(name="tbl_elections")
public class Election extends IdentifiedDomainObject {

    @Embedded
    private ElectionId electionId;

    @Column(name="name")
    private String name;

    @Embedded
    private ElectionDate electionDate;

    @Enumerated(EnumType.STRING)
    private ElectionType type;

    @ManyToOne
    @JoinColumn(name="city_id", referencedColumnName = "id")
    private City city;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "election", orphanRemoval = true)
    private List<Candidate> candidates;

    protected Election() {
        super();
    }

    public Election(
            ElectionId electionId,
            String name,
            Date electionDate,
            Date electionCampaignStart,
            Date electionCampaignEnd,
            City city,
            ElectionType type) {
        this();

        this.setElectionId(electionId);
        this.setName(name);
        this.setElectionDate(new ElectionDate(
                electionDate,
                electionCampaignStart,
                electionCampaignEnd
            ));
        this.setCity(city);
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

    public List<Candidate> candidates() { return this.candidates; }

    @Override
    public boolean equals(Object obj) {
        boolean equalObject = false;

        if (obj != null && obj.getClass() == this.getClass()) {
            Election typedObject = (Election) obj;
            equalObject = typedObject.electionId().equals(this.electionId());
        }
        return equalObject;
    }

    public void setElectionId(ElectionId anElectionId) {
        this.assertArgumentNotNull(anElectionId, "Election Id must not be null");
        this.assertArgumentNotEmpty(anElectionId.id(), "Election Id must not be empty");

        this.electionId = anElectionId;
    }

    public void setName(String name) {
        this.assertArgumentNotNull(name, "Election name must not be null");
        this.assertArgumentNotEmpty(name, "Election name must not be empty");

        this.name = name;
    }

    public void setElectionDate(ElectionDate electionDate) {
        this.assertArgumentNotNull(electionDate, "Election Dates must not be null");

        this.electionDate = electionDate;
    }

    public void setType(ElectionType type) {
        this.assertArgumentNotNull(type, "Election type must no be empty");

        this.type = type;
    }

    public City city() {
        return this.city;
    }

    public void setCity(City city) {
        this.assertArgumentNotNull(city, "City is required");

        this.city = city;
    }
}
