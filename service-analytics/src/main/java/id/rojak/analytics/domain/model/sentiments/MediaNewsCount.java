package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.news.SentimentType;

/**
 * Created by inagi on 7/20/17.
 */
public class MediaNewsCount extends ValueObject {

    private MediaId mediaId;
    private Media media;

    private Long positiveNews;
    private Long negativeNews;
    private Long neutralNews;

    public MediaNewsCount(MediaId mediaId,
                          Long positiveNews,
                          Long negativeNews,
                          Long neutralNews) {
        super();

        this.mediaId = mediaId;
        this.positiveNews = positiveNews;
        this.negativeNews = negativeNews;
        this.neutralNews = neutralNews;
    }

    public void insert(SentimentType sentimentType, Long withCount) {

        if (sentimentType.isPositive()) {
            this.setTotalPositiveNews(withCount);

        } else if (sentimentType.isNegative()) {
            this.setTotalNegativeNews(withCount);

        } else if (sentimentType.isNeutral()) {
            this.setTotalNeutralNews(withCount);
        }

    }

    public MediaNewsCount(MediaId mediaId) {
        this(mediaId, 0L, 0L, 0L);
    }

    public MediaId mediaId() {
        return mediaId;
    }

    public Media media() { return media; }

    public Long totalPositiveNews() {
        return this.positiveNews;
    }

    public Long totalNegativeNews() {
        return this.negativeNews;
    }

    public Long totalNeutralNews() {
        return this.neutralNews;
    }

    public void setTotalPositiveNews(Long positive) {
        this.positiveNews = positive;
    }

    public void setTotalNegativeNews(Long negative) {
        this.negativeNews = negative;
    }

    public void setTotalNeutralNews(Long neutral) {
        this.neutralNews = neutral;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
