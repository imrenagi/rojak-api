package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.news.SentimentType;

/**
 * Created by inagi on 7/31/17.
 */
public class NewsSentimentCount extends ValueObject {

    private Long positiveNews;
    private Long negativeNews;
    private Long neutralNews;

    public NewsSentimentCount(
            Long positiveNews,
            Long negativeNews,
            Long neutralNews) {
        super();
        this.positiveNews = positiveNews;
        this.negativeNews = negativeNews;
        this.neutralNews = neutralNews;
    }

    public NewsSentimentCount() {
        this(0L, 0L, 0L);
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

}
