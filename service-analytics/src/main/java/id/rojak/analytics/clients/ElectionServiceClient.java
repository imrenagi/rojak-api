package id.rojak.analytics.clients;

import id.rojak.analytics.resource.dto.CandidateDTO;
import id.rojak.analytics.resource.dto.ElectionDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by inagi on 7/28/17.
 */
@FeignClient(name = "service-election", fallback = ElectionServiceClientFallback.class)
public interface ElectionServiceClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/elections/{election_id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ElectionDTO election(@PathVariable("election_id") String anElectionId);

    @RequestMapping(method = RequestMethod.GET,
            value = "/elections/{election_id}/candidates/{candidate_id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CandidateDTO candidate(@PathVariable("election_id") String anElectionId,
                           @PathVariable("candidate_id") String candidateId);

}

@Component
class ElectionServiceClientFallback implements ElectionServiceClient {

    @Override
    public ElectionDTO election(String anElectionId) {
        return null;
    }

    @Override
    public CandidateDTO candidate(String anElectionId, String candidateId) {
        return null;
    }
}