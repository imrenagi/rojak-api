package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.candidate.CandidateId;

import java.util.Collection;

/**
 * Created by inagi on 7/31/17.
 */
public interface TopCandidateCalculator {

    CandidateId topCandidateFrom(Collection<CandidateNewsCount> candidateNewsCounts);
}
