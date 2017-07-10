package id.rojak.election.resource;

import id.rojak.election.application.candidate.CandidateApplicationService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<CandidateDTO> candidates = candidatesPage.map(candidate -> new CandidateDTO(candidate))
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


}


