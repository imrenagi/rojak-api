package id.rojak.election.common.domain.model;

import java.util.Date;

/**
 * Created by inagi on 7/3/17.
 */
public interface DomainEvent {

    public int eventVersion();
    public Date occuredOn();

}
