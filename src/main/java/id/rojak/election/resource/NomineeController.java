package id.rojak.election.resource;

import id.rojak.election.application.candidate.NewNomineeCommand;
import id.rojak.election.application.candidate.NomineeApplicationService;
import id.rojak.election.application.candidate.UpdateNomineeCommand;
import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.domain.model.candidate.Nominee;
import id.rojak.election.resource.dto.MetaDTO;
import id.rojak.election.resource.dto.NomineeDTO;
import id.rojak.election.resource.dto.NomineesCollectionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(path = "/nominees", method = RequestMethod.POST)
    public ResponseEntity<NomineeDTO> createNominee(@Valid @RequestBody NewNomineeCommand aCommand) {

        Nominee nominee = nomineeApplicationService.newNominee(aCommand);

        return new ResponseEntity<NomineeDTO>(
                new NomineeDTO(nominee),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/nominees/{nominee_id}", method = RequestMethod.PUT)
    public ResponseEntity<NomineeDTO> updateNominee(
            @PathVariable("nominee_id") String aNomineeId,
            @Valid @RequestBody UpdateNomineeCommand aCommand) {

        Nominee nominee = nomineeApplicationService.updateNominee(aNomineeId, aCommand);

        return new ResponseEntity<NomineeDTO>(
                new NomineeDTO(nominee),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/nominees/{nominee_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeNominee(@PathVariable("nominee_id") String aNomineeId) {

        nomineeApplicationService.removeNominee(aNomineeId);

        return new ResponseEntity<String>("", HttpStatus.OK);
    }


}
