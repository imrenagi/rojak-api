package id.rojak.election.application.candidate;

import id.rojak.election.domain.model.candidate.Candidate;

/**
 * Created by inagi on 8/8/17.
 */
public interface ICandidateApplicationService {

    public Candidate candidate(String anElectionId,
                               String candidateId);
    public Candidate newCandidate(NewCandidateCommand aCommand);
    public void removeCandidate(RemoveCandidateCommand aCommand);
}
