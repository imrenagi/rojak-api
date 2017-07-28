package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.common.domain.model.IdentifiedDomainObject;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by imrenagi on 7/14/17.
 */
@Entity
@Table(name="tbl_news")
public class News extends IdentifiedDomainObject {

    private String title;
    private String url;
    private String content;

    @Embedded
    private NewsId newsId;

    @Embedded
    private MediaId mediaId;

    @Embedded
    private ElectionId electionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id_id", referencedColumnName = "id")
    private Media media;

    private Date timestamp; //date of the news from the media

    @Column(name="created_at")
    private Date createdAt;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "news", orphanRemoval = true)
    private List<NewsSentiment> sentiments;

    protected News() {}

    public News(NewsId newsId,
                String title,
                String url,
                String content,
                MediaId mediaId,
                ElectionId electionId,
                Date timestamp) {
        this();

        this.setNewsId(newsId);
        this.setTitle(title);
        this.setUrl(url);
        this.setContent(content);
        this.setMediaId(mediaId);
        this.setElectionId(electionId);
        this.setTimestamp(timestamp);

        this.createdAt = new Date();
    }

    public void addSentiment(NewsSentiment aSentiment) {
        this.assertArgumentNotNull(aSentiment, "Sentiment is required and can't be null");

        if (this.sentiments == null) {
            this.sentiments = new ArrayList<>();
        }

        this.sentiments.add(aSentiment);

        //TODO publish event if necessary
    }

    public String title() {
        return this.title;
    }

    public String url() {
        return this.url;
    }

    public String content() {
        return this.content;
    }

    public MediaId mediaId() {
        return this.mediaId;
    }

    public Media media() {
        return this.media;
    }

    public Date timestamp() {
        return this.timestamp;
    }

    public ElectionId electionId() {
        return this.electionId;
    }

    public NewsId newsId() {
        return this.newsId;
    }

    public List<NewsSentiment> sentiments() {
        return this.sentiments;
    }

    public void setElectionId(ElectionId electionId) {
        this.assertArgumentNotNull(electionId, "Election ID is required");

        this.electionId = electionId;
    }

    public void setTitle(String title) {
        this.assertArgumentNotNull(title, "Title is required");
        this.assertArgumentNotEmpty(title, "Title can't be empty");

        this.title = title;
    }

    public void setUrl(String url) {
        this.assertArgumentNotNull(url, "News url is required");
        this.assertArgumentNotNull(url, "News url can't be empty");
        //TODO check url validity

        this.url = url;
    }

    public void setContent(String content) {
        this.assertArgumentNotNull(content, "Content is required");
        this.assertArgumentNotEmpty(content, "Content may not be empty");

        this.content = content;
    }

    public void setNewsId(NewsId newsId) {
        this.assertArgumentNotNull(newsId, "News Id is required");

        this.newsId = newsId;
    }

    public void setMediaId(MediaId mediaId) {
        this.assertArgumentNotNull(mediaId, "Media Id is required");

        this.mediaId = mediaId;
    }

    public void setMedia(Media media) {
        this.assertArgumentNotNull(media, "Media can't be null");

        this.media = media;
    }

    public void setTimestamp(Date timestamp) {
        this.assertArgumentNotNull(timestamp, "News timestamp is required");

        this.timestamp = timestamp;
    }

    public void setCreatedAt(Date createdAt) {
        this.assertArgumentNotNull(createdAt, "Created At can't be null");

        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO implement this
        return super.equals(obj);
    }


    public void insertTo(Media media) {
        this.assertArgumentNotNull(media, "Media can't be null!");

        this.setMedia(media);
        media.addNews(this);
    }
}
