package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.candidate.CandidateId;

/**
 * Created by inagi on 7/31/17.
 */
public class CandidateNewsCount extends NewsSentimentCount {

    private CandidateId candidateId;

    public CandidateNewsCount(CandidateId candidateId,
                              Long positiveNews,
                              Long negativeNews,
                              Long neutralNews) {
        super(positiveNews, negativeNews, neutralNews);
        this.candidateId = candidateId;
    }

    public CandidateNewsCount(CandidateId candidateId) {
        super(0L,0L,0L);
        this.candidateId = candidateId;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }
}
