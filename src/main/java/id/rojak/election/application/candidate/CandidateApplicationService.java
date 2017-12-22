package id.rojak.election.application.candidate;

import id.rojak.common.error.ResourceNotFoundException;
import id.rojak.election.client.AnalyticsServiceClientImpl;
import id.rojak.election.client.IAnalyticsServiceClient;
import id.rojak.election.domain.model.candidate.*;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.domain.model.election.ElectionId;
import id.rojak.election.domain.model.election.ElectionRepository;
import id.rojak.election.resource.ElectionController;
import id.rojak.election.resource.dto.StatisticDTO;
import id.rojak.election.resource.dto.StatisticSummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imrenagi on 7/8/17.
 */
@Service
public class CandidateApplicationService { //implements ICandidateApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ElectionController.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
//    private IAnalyticsServiceClient analyticsService;
    private AnalyticsServiceClientImpl analyticsService;

    @Transactional
    public Page<Candidate> allCandidates(String anElectionId, Pageable pageRequest) {

        Page<Candidate> result = this.candidateRepository
                .findByElectionId(new ElectionId(anElectionId),
                        pageRequest);

        return result;
    }

    @Transactional
    public Page<Candidate> allCandidates(Pageable pageRequest) {

        Page<Candidate> result = this.candidateRepository.findAll(pageRequest);
        return result;
    }

    public StatisticDTO candidateStatistic(String anElectionId, String candidateId) {
        return analyticsService.newsStatistic(
                anElectionId,
                candidateId);
    }

    public StatisticSummaryDTO summaryStatisticOf(String anElectionId, String candidateid) {

        return analyticsService.candidateSummaryStatistic(anElectionId, candidateid);

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
    public Candidate candidate(String candidateId) {

        Candidate candidate = this.candidateRepository
                .findByCandidateId(new CandidateId(candidateId));

        if (candidate == null) {
            throw new ResourceNotFoundException(
                    String.format("Candidate %s is not found", candidateId));
        }

        return candidate;
    }

    @Transactional
    public Candidate newCandidate(NewCandidateCommand aCommand) {

        Election election = this.election(
                aCommand.getElectionId());

        Nominee mainCandidate = this.nominee(
                aCommand.getMainCandidateId());

        Nominee viceCandidate = this.nominee(
                aCommand.getViceCandidateId());

        //TODO this snippet can be in domain service instead
        if (mainCandidate.isParticipatedIn(election)
                || viceCandidate.isParticipatedIn(election)) {
            throw new IllegalArgumentException(String.format(
                    "Nominee/s has/have been participating in %s", election.name()));
        }
        //TODO add new key to candidate, candidate number
        //create domain service

        //TODO validate candidate number

        Candidate existingCandidate =
                this.candidateRepository
                        .findByCandidateId(new CandidateId(aCommand.getId()));

        if (existingCandidate != null) {
            throw new IllegalArgumentException(
                    String.format("Candidate with id %s is exist",
                            aCommand.getId()));
        }

        Candidate candidate = new Candidate(
                new CandidateId(aCommand.getId()),
                new ElectionId(aCommand.getElectionId()),
                aCommand.getCandidateNumber(),
                aCommand.getImageUrl(),
                mainCandidate,
                viceCandidate,
                new SocialMediaInformation(
                        aCommand.getWebUrl(),
                        aCommand.getTwitterId(),
                        aCommand.getInstagramId(),
                        aCommand.getFacebookUrl()));
        candidate.setElection(election);

        election.addCandidate(candidate);

        return candidate;
    }

    @Transactional
    public Candidate updateCandidate(String candidateId, UpdateCandidateDetailCommand aCommand) {

        Nominee mainCandidate = this.nominee(
                aCommand.getMainCandidateId());

        Nominee viceCandidate = this.nominee(
                aCommand.getViceCandidateId());

        Candidate existingCandidate =
                this.candidateRepository
                        .findByCandidateId(new CandidateId(candidateId));

        if (existingCandidate == null) {
            throw new IllegalArgumentException(
                    String.format("Candidate with id %s doesn't exist",
                            candidateId));
        }

        existingCandidate.setCandidateNumber(aCommand.getCandidateNumber());
        existingCandidate.setImageUrl(aCommand.getImageUrl());
        existingCandidate.setMainCandidate(mainCandidate);
        existingCandidate.setViceCandidate(viceCandidate);
        existingCandidate.setSocialMediaInformation(new SocialMediaInformation(
                aCommand.getWebUrl(),
                aCommand.getTwitterId(),
                aCommand.getInstagramId(),
                aCommand.getFacebookUrl()));

        Election newElection = this.election(aCommand.getElectionId());
        Election oldElection = existingCandidate.election();

//        if (!newElection.equals(oldElection)) {
//            existingCandidate.setElectionId(new ElectionId(aCommand.getElectionId()));
//
//            newElection.addCandidate(existingCandidate);
//
//            oldElection.removeCandidate(existingCandidate);
//        }

        existingCandidate.setElectionId(new ElectionId(aCommand.getElectionId()));
        existingCandidate.changeElection(newElection);

        return existingCandidate;
    }

    @Transactional
    public void removeCandidate(String aCandidateId) {
        Candidate candidate = this.candidate(aCandidateId);
        Election election = this.election(candidate.electionId().id());
        election.removeCandidate(candidate);
    }

    @Transactional
    private Election election(String anElectionId) {
        Election election = this.electionRepository.findByElectionId(
                new ElectionId(anElectionId));

        if (election == null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s is not found", anElectionId));
        }

        return election;
    }

    @Transactional
    private Nominee nominee(String aNomineeId) {
        Nominee nominee = nomineeRepository
                .findByNomineeId(new NomineeId(aNomineeId));

        if (nominee == null) {
            throw new ResourceNotFoundException(
                    String.format("Nominee  is not found"));
        }

        return nominee;
    }

}
