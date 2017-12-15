package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;

/**
 * Created by imrenagi on 9/24/17.
 */
public class CandidateNewsCounter extends SentimentCounter {

    private ElectionId electionId;
    private CandidateId candidateId;
    private MediaId mediaId;

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

    public CandidateNewsCounter(ElectionId electionId,
                                CandidateId candidateId,
                                MediaId mediaId,
                                long positive,
                                long negative,
                                long neutral) {
        super(positive, negative, neutral);
        this.mediaId = mediaId;
        this.electionId = electionId;
        this.candidateId = candidateId;
    }

    public ElectionId electionId() {
        return electionId;
    }

    public CandidateId candidateId() {
        return candidateId;
    }

    public MediaId mediaId() {
        return mediaId;
    }
}
