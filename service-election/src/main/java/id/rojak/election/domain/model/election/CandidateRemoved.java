package id.rojak.election.domain.model.election;

import id.rojak.election.common.domain.model.DomainEvent;
import id.rojak.election.domain.model.candidate.CandidateId;

import java.util.Date;

/**
 * Created by inagi on 7/4/17.
 */
public class CandidateRemoved implements DomainEvent {

    private ElectionId electionId;
    private CandidateId candidateId;
    private int eventVersion;
    private Date occuredOn;

    public CandidateRemoved(ElectionId electionId, CandidateId candidateId) {
        super();

        this.eventVersion = 1;
        this.occuredOn = new Date();
        this.electionId = electionId;
        this.candidateId = candidateId;
    }

    public ElectionId electionId() { return this.electionId; }

    public CandidateId candidateId() { return this.candidateId; }

    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    @Override
    public Date occuredOn() {
        return this.occuredOn;
    }
}
