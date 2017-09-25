package id.rojak.election.client;

import id.rojak.election.resource.dto.StatisticDTO;
import id.rojak.election.resource.dto.StatisticSummaryDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by inagi on 7/26/17.
 */
@FeignClient(name = "service-analytics", fallback = AnalyticsServiceClientFallback.class)
public interface AnalyticsServiceClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/analytics/elections/{election_id}/candidates/{candidate_id}/stat_summary",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    StatisticSummaryDTO candidateSummaryStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId
    );

    @RequestMapping(method = RequestMethod.GET,
            value = "/analytics/elections/{election_id}/candidates/{candidate_id}/news_stat",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    StatisticDTO newsStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId
    );
}

@Component
class AnalyticsServiceClientFallback implements AnalyticsServiceClient {

    @Override
    public StatisticSummaryDTO candidateSummaryStatistic(@PathVariable("election_id") String anElectionId,
                                                         @PathVariable("candidate_id") String aCandidateId) {
        return new StatisticSummaryDTO();
    }

    @Override
    public StatisticDTO newsStatistic(@PathVariable("election_id") String anElectionId,
                                      @PathVariable("candidate_id") String aCandidateId) {
        return new StatisticDTO();
    }
}
