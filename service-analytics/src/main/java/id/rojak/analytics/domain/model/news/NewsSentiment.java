package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.common.domain.model.IdentifiedDomainObject;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by imrenagi on 7/16/17.
 */
@Entity
@Table(name = "tbl_news_sentiments")
public class NewsSentiment extends IdentifiedDomainObject {

    @Embedded
    private ElectionId electionId;

    @Embedded
    private CandidateId candidateId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sentiment_type")
    private SentimentType sentimentType;

    @Embedded
    private MediaId mediaId;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    private News news;

    protected NewsSentiment() {
    }

    public NewsSentiment(MediaId mediaId,
                         ElectionId electionId,
                         CandidateId candidateId,
                         Date newsTimestamp,
                         SentimentType sentimentType) {
        this();

        this.setMediaId(mediaId);
        this.setElectionId(electionId);
        this.setCandidateId(candidateId);
        this.setSentimentType(sentimentType);
        this.setCreatedAt(newsTimestamp);

    }

    public Date newsCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.assertArgumentNotNull(createdAt, "Date of creation of news is required");

        if (new Date().before(createdAt)) {
            throw new IllegalArgumentException("Can't create sentiment for future news");
        }

        this.createdAt = createdAt;
    }

    public void setElectionId(ElectionId electionId) {
        this.assertArgumentNotNull(electionId, "Election Id is required");

        this.electionId = electionId;
    }

    public void setCandidateId(CandidateId candidateId) {
        this.assertArgumentNotNull(candidateId, "Candidate Id is required");

        this.candidateId = candidateId;
    }

    public void setSentimentType(SentimentType sentimentType) {
        this.assertArgumentNotNull(sentimentType, "Sentiment result is required");

        this.sentimentType = sentimentType;
    }

    public void setMediaId(MediaId mediaId) {
        this.assertArgumentNotNull(mediaId, "Media Id is required");

        this.mediaId = mediaId;
    }

    public void setNews(News news) {
        this.assertArgumentNotNull(news, "News should not be null");

        this.news = news;
    }

    public ElectionId electionId() {
        return this.electionId;
    }

    public CandidateId candidateId() {
        return this.candidateId;
    }

    public SentimentType type() {
        return this.sentimentType;
    }

    public MediaId mediaId() {
        return this.mediaId;
    }

    public Date createdAt() {
        return this.createdAt;
    }

    public News news() {
        return this.news;
    }

    public void insertTo(News news) {
        this.assertArgumentNotNull(news, "News can't be null!");

        this.setNews(news);
        news.addSentiment(this);
    }

    public String shortText() {
        return String.format("%s %s", this.type().text(), this.candidateId().id());
    }
}
