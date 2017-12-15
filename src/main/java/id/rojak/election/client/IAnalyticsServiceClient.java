package id.rojak.election.client;

import id.rojak.election.resource.dto.StatisticDTO;
import id.rojak.election.resource.dto.StatisticSummaryDTO;

/**
 * Created by inagi on 7/26/17.
 */
public interface IAnalyticsServiceClient {
    StatisticSummaryDTO candidateSummaryStatistic(String anElectionId, String aCandidateId);

    StatisticDTO newsStatistic(String anElectionId, String aCandidateId);
}
