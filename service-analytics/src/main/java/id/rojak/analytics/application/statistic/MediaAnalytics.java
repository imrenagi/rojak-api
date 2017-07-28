package id.rojak.analytics.application.statistic;

import id.rojak.analytics.domain.model.candidate.CandidateId;

import java.util.List;

/**
 * Created by inagi on 7/28/17.
 */
public interface MediaAnalytics {

    CandidateId supportedCandidate(List<? extends  Object> candidates);

}
