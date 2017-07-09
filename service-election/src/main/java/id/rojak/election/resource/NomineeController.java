package id.rojak.election.resource;

import id.rojak.election.application.candidate.NomineeApplicationService;
import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.domain.model.candidate.Candidate;
import id.rojak.election.domain.model.candidate.Nominee;
import id.rojak.election.resource.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by imrenagi on 7/9/17.
 */
@RestController
public class NomineeController {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private NomineeApplicationService nomineeApplicationService;

    @RequestMapping(path = "/nominees", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<NomineesCollectionDTO> getAllCandidates(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="limit", defaultValue="10") Integer size) {


        Page<Nominee> nomineesPage = nomineeApplicationService.allNominees(new PageRequest(page, size));

        List<NomineeDTO> nominees = nomineesPage.map(nominee -> new NomineeDTO(nominee))
                .getContent();

        return new ResponseEntity<>(
                new NomineesCollectionDTO(
                        nominees,
                        new MetaDTO(
                                page,
                                nomineesPage.getTotalPages(),
                                nomineesPage.getTotalElements())),
                HttpStatus.OK);

    }
}
