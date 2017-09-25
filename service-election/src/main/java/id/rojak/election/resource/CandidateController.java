package id.rojak.election.resource;

import id.rojak.election.application.candidate.*;
import id.rojak.election.domain.model.candidate.Candidate;
import id.rojak.election.resource.dto.CandidateCollectionDTO;
import id.rojak.election.resource.dto.CandidateDTO;
import id.rojak.election.resource.dto.MetaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by imrenagi on 7/8/17.
 */
@RestController
public class CandidateController {

    private static final Logger log = LoggerFactory.getLogger(ElectionController.class);

    @Autowired
    private CandidateApplicationService candidateApplicationService;

    @RequestMapping(path = "/{election_id}/candidates", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CandidateCollectionDTO> getAllCandidates(
            @PathVariable("election_id") String anElectionId,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="limit", defaultValue="10") Integer size) {

        Page<Candidate> candidatesPage = this.candidateApplicationService
                    .allCandidates(
                            anElectionId,
                            new PageRequest(page, size));

        List<CandidateDTO> candidates = candidatesPage
                .map(candidate -> {
                    CandidateDTO dto = new CandidateDTO(candidate);
                    dto.setStatistic(
                            this.candidateApplicationService.candidateStatistic(
                                    anElectionId,
                                    candidate.candidateId().id())
                    );
                    return dto;
                })
                .getContent();

        return new ResponseEntity<>(
                new CandidateCollectionDTO(
                        candidates,
                        new MetaDTO(
                                page,
                                candidatesPage.getTotalPages(),
                                candidatesPage.getTotalElements())),
                HttpStatus.OK);

    }

    @RequestMapping(path = "/{election_id}/candidates/{candidate_id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CandidateDTO> getCandidateFromElection(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        Candidate candidate = this.candidateApplicationService.candidate(anElectionId, aCandidateId);

        return new ResponseEntity<CandidateDTO>(
                new CandidateDTO(candidate), HttpStatus.OK);
    }

    @RequestMapping(path = "/{election_id}/candidates", method = RequestMethod.POST)
    public ResponseEntity<CandidateDTO> newCandidateForElection(@PathVariable("election_id") String anElectionId,
                                                          @Valid @RequestBody NewCandidateCommand aCommand) {

        aCommand.setElectionId(anElectionId);

        Candidate candidate = this.candidateApplicationService.newCandidate(aCommand);

        return new ResponseEntity<CandidateDTO>(
                new CandidateDTO(candidate), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{election_id}/candidates/{candidate_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCandidateForAnElection(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String candidateId,
            @Valid @RequestBody UpdateCandidateDetailCommand aCommand) {

        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/{election_id}/candidates/{candidate_id}/socmed", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCandidateSocialMediaInElection(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String candidateId,
            @Valid @RequestBody UpdateCandidateSocialMediaCommand aCommand) {

        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/{election_id}/candidates/{candidate_id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteCandidateOfAnElection(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String candidateId) {

        this.candidateApplicationService.removeCandidate(
                new RemoveCandidateCommand(anElectionId, candidateId));

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(path = "/{election_id}/candidates/{candidate_id}/statistics", method = RequestMethod.GET)
    public ResponseEntity<String> candidateStatisticOverTheTime(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String candidateId) {

        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/{election_id}/candidates/{candidate_id}/media", method = RequestMethod.GET)
    public ResponseEntity<String> allMediaStatisticForCandidate(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String candidateId) {

        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

}


