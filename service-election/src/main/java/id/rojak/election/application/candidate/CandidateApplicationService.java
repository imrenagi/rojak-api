package id.rojak.election.application.candidate;

import id.rojak.election.common.error.ResourceNotFoundException;
import id.rojak.election.domain.model.candidate.*;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.domain.model.election.ElectionId;
import id.rojak.election.domain.model.election.ElectionRepository;
import id.rojak.election.resource.ElectionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imrenagi on 7/8/17.
 */
@Service
public class CandidateApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ElectionController.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private ElectionCandidateService electionCandidateService;

    @Transactional
    public Page<Candidate> allCandidates(String anElectionId, Pageable pageRequest) {

        Page<Candidate> result = this.candidateRepository
                .findByElectionId(new ElectionId(anElectionId),
                        pageRequest);

        return result;
    }

    @Transactional
    public Candidate candidate(String anElectionId,
                               String candidateId) {

        Candidate candidate = this.candidateRepository
                .findByElectionIdAndCandidateId(new ElectionId(anElectionId),
                        new CandidateId(candidateId));

        if (candidate == null) {
            throw new ResourceNotFoundException(
                    String.format("Candidate %s in election %s is not found", candidateId, anElectionId));
        }

        return candidate;
    }

    @Transactional
    public Candidate newCandidate(NewCandidateCommand aCommand) {

        Election election = this.electionRepository.findByElectionId(
                new ElectionId(aCommand.getElectionId()));

        if (election == null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s is not found", aCommand.getElectionId()));
        }

        Nominee mainCandidate = nomineeRepository
                .findByNomineeId(new NomineeId(aCommand.getMainCandidateId()));

        Nominee viceCandidate = nomineeRepository
                .findByNomineeId(new NomineeId(aCommand.getViceCandidateId()));

        if (mainCandidate == null) {
            throw new ResourceNotFoundException(
                    String.format("Main Candidate is not found"));
        }

        if (viceCandidate == null) {
            throw new ResourceNotFoundException(
                    String.format("Vice Candidate is not found"));
        }

        //TODO validate candidate number
        //TODO check duplication of candidate in the same election
        //TODO One nominee should only be participated as one candidate

        Candidate candidate = new Candidate(
                new CandidateId(this.candidateRepository.nextId()),
                new ElectionId(aCommand.getElectionId()),
                aCommand.getCandidateNumber(),
                mainCandidate,
                viceCandidate,
                new SocialMediaInformation(
                        aCommand.getWebUrl(),
                        aCommand.getTwitterId(),
                        aCommand.getInstagramId(),
                        aCommand.getFacebookUrl()));
        candidate.setElection(election);

        candidate = this.candidateRepository.save(candidate);

        return candidate;
    }

    @Transactional
    public void removeCandidate(RemoveCandidateCommand aCommand) {
        //TODO implement!
    }

}
