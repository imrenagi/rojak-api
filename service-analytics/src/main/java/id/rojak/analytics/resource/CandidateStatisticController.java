package id.rojak.analytics.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class CandidateStatisticController {

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/statistics",
            method = RequestMethod.GET)
    public ResponseEntity<String> candidateAccumulativeStatisticOvertime(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/medias",
            method = RequestMethod.GET)
    public ResponseEntity<String> candidateMediaList(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/stat_summary",
            method = RequestMethod.GET)
    public ResponseEntity<String> candidateMediaSummaryStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

}
