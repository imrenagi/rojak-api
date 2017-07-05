package id.rojak.election.domain.model.election;

import id.rojak.election.common.domain.model.DomainEvent;
import id.rojak.election.domain.model.candidate.Candidate;

import java.util.Date;

/**
 * Created by inagi on 7/3/17.
 */
public class CandidateAdded implements DomainEvent {

    private Candidate candidate;
    private int eventVersion;
    private Date occuredOn;
    private ElectionId electionId;
    private String username;

    public CandidateAdded(Candidate candidate,
                          ElectionId electionId,
                          String username) {
        super();

        this.candidate = candidate;
        this.eventVersion = 1;
        this.occuredOn = new Date();
        this.electionId = electionId;
        this.username = username;
    }

    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    @Override
    public Date occuredOn() {
        return this.occuredOn;
    }

    public ElectionId electionId() {
        return this.electionId;
    }

    public String userName() {
        return this.username;
    }

    public Candidate candidate() {
        return this.candidate;
    }
}
