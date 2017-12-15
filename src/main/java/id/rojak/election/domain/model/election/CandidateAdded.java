package id.rojak.election.domain.model.election;

import id.rojak.common.domain.model.DomainEvent;
import id.rojak.election.domain.model.candidate.CandidateId;

import java.util.Date;

/**
 * Created by inagi on 7/3/17.
 */
public class CandidateAdded implements DomainEvent {

    private CandidateId candidateId;
    private int eventVersion;
    private Date occuredOn;
    private ElectionId electionId;

    public CandidateAdded(ElectionId electionId,
                          CandidateId candidateId) {
        super();

        this.candidateId = candidateId;
        this.eventVersion = 1;
        this.occuredOn = new Date();
        this.electionId = electionId;
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

    public CandidateId candidateId() { return this.candidateId; }

}
