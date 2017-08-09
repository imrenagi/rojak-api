package id.rojak.election.application.candidate;

import id.rojak.election.domain.model.candidate.Nominee;

/**
 * Created by inagi on 8/8/17.
 */
public interface INomineeApplicationService {

    public Nominee newNominee(NewNomineeCommand aCommand);
}
