package id.rojak.election.domain.model.election;

import id.rojak.common.domain.model.DomainEventPublisher;
import id.rojak.common.domain.model.IdentifiedDomainObject;
import id.rojak.election.domain.model.candidate.Candidate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by inagi on 7/3/17.
 */
@Entity
@Table(name = "tbl_elections")
public class Election extends IdentifiedDomainObject {

    @Embedded
    private ElectionId electionId;

    @Column(name = "name")
    private String name;

    @Embedded
    private ElectionDate electionDate;

    @Enumerated(EnumType.STRING)
    private ElectionType type;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

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

        this.candidates = new ArrayList<>(0);

        //TODO handle other variable initialization

        //TODO publish event about new electio n creation
        DomainEventPublisher.instance()
                .publish(new ElectionCreated());
    }

    public Election(
            ElectionId electionId,
            String name,
            Date electionDate,
            Date electionCampaignStart,
            Date electionCampaignEnd,
            Province province,
            City city,
            ElectionType type) {

        this(electionId, name, electionDate, electionCampaignStart, electionCampaignEnd, city, type);
        this.setProvince(province);
    }

    public void addCandidate(Candidate candidate) {
        this.assertArgumentNotNull(candidate, "Candidate must not be null");

        this.candidates().add(candidate);

        DomainEventPublisher
                .instance()
                .publish(new CandidateAdded(this.electionId(),
                        candidate.candidateId()));
    }

    public void removeCandidate(Candidate candidate) {
        this.assertArgumentNotNull(candidate, "Candidate must not be null");

        this.candidates().remove(candidate);

        DomainEventPublisher
                .instance()
                .publish(new CandidateRemoved(
                        this.electionId(),
                        candidate.candidateId()));
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

    public List<Candidate> candidates() {
        return this.candidates;
    }

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

    public Province province() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.assertArgumentNotNull(province, "Province is required");

        this.province = province;
    }
}
