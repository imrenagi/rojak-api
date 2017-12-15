package id.rojak.analytics.clients;

import id.rojak.analytics.resource.dto.CandidateDTO;
import id.rojak.analytics.resource.dto.ElectionDTO;
import id.rojak.election.application.candidate.CandidateApplicationService;
import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.domain.model.candidate.Candidate;
import id.rojak.election.domain.model.election.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by imrenagi on 12/11/17.
 */
@Component
public class ElectionServiceClientImpl {// implements ElectionServiceClient {

    @Autowired
    ElectionApplicationService electionApplicationService;

    @Autowired
    CandidateApplicationService candidateApplicationService;

    public ElectionDTO election(String anElectionId) {

        Election election = this.electionApplicationService
                .election(anElectionId);

        return new ElectionDTO(election.electionId().id(),
                election.name());
    }

    public CandidateDTO candidate(String anElectionId, String candidateId) {
        Candidate candidate = this.candidateApplicationService
                .candidate(anElectionId, candidateId);

        return new CandidateDTO(
                candidate.candidateNumber(),
                candidate.mainCandidate().fullName() + " & " + candidate.viceCandidate().fullName(),
                candidate.mainCandidate().nickName() + " & " + candidate.viceCandidate().nickName());
    }

}