package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.news.SentimentType;

/**
 * Created by inagi on 7/20/17.
 */
public class MediaNewsCount extends NewsSentimentCount {

    private MediaId mediaId;

    public MediaNewsCount(MediaId mediaId,
                          Long positiveNews,
                          Long negativeNews,
                          Long neutralNews) {
        super(positiveNews, negativeNews, neutralNews);
        this.mediaId = mediaId;
    }

    public MediaNewsCount(MediaId mediaId) {
        this(mediaId, 0L, 0L, 0L);
    }

    public MediaId mediaId() {
        return mediaId;
    }
}
