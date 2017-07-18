package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;

/**
 * Created by imrenagi on 7/16/17.
 */
public class SentimentAggregate extends ValueObject {

    private ElectionId electionId;
    private CandidateId candidateId;
    private MediaId mediaId;
    private SentimentType sentimentType;
    private Long count;

    public SentimentAggregate(ElectionId electionId,
                              CandidateId candidateId,
                              MediaId mediaId,
                              SentimentType sentimentType,
                              Long count) {
        super();
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.mediaId = mediaId;
        this.sentimentType = sentimentType;
        this.count = count;
    }

    public ElectionId getElectionId() {
        return electionId;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public MediaId getMediaId() {
        return mediaId;
    }

    public SentimentType getSentimentType() {
        return sentimentType;
    }

    public Long getCount() {
        return count;
    }
}
