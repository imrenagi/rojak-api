package id.rojak.election.domain.model.election;

import id.rojak.election.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * Created by inagi on 7/4/17.
 */
public class CandidateRemoved implements DomainEvent {

    @Override
    public int eventVersion() {
        return 0;
    }

    @Override
    public Date occuredOn() {
        return null;
    }
}
