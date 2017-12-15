package id.rojak.election.client;

import id.rojak.election.resource.dto.StatisticDTO;
import id.rojak.election.resource.dto.StatisticSummaryDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by imrenagi on 12/9/17.
 */
@Service
public class AnalyticsServiceClientImpl {// implements IAnalyticsServiceClient {

//    @Override
    public StatisticSummaryDTO candidateSummaryStatistic(String anElectionId, String aCandidateId) {
        return new StatisticSummaryDTO();
    }

//    @Override
    public StatisticDTO newsStatistic(String anElectionId, String aCandidateId) {
        return new StatisticDTO();
    }
}
