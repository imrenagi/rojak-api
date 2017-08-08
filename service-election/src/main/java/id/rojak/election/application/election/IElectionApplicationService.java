package id.rojak.election.application.election;

import id.rojak.election.domain.model.election.Election;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by inagi on 8/8/17.
 */
public interface IElectionApplicationService {

    public Page<Election> allElections(Pageable pageRequest);
    public Election election(String anElectionId);
    public Election newElection(NewElectionCommand aCommand);
    public void changeElectionDetail(ElectionDetailChangeCommand aCommand);

}
