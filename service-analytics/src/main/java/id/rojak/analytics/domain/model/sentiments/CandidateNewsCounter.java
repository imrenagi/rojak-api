package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;

/**
 * Created by imrenagi on 9/24/17.
 */
public class CandidateNewsCounter extends SentimentCounter {

    private ElectionId electionId;
    private CandidateId candidateId;

    protected CandidateNewsCounter() {}

    public CandidateNewsCounter(ElectionId electionId,
                                CandidateId candidateId,
                                long positive,
                                long negative,
                                long neutral) {
        super(positive, negative, neutral);
        this.electionId = electionId;
        this.candidateId = candidateId;
    }
}
