package id.rojak.election.client;

import id.rojak.election.resource.dto.StatisticSummaryDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by inagi on 7/26/17.
 */
@FeignClient(name = "service-analytics")
public interface AnalyticsServiceClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/analytics/elections/{election_id}/candidates/{candidate_id}/stat_summary",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    StatisticSummaryDTO candidateSummaryStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId
    );

}
