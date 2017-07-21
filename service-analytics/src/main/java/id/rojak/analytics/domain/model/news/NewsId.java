package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by imrenagi on 7/14/17.
 */
@Embeddable
public class NewsId extends ValueObject {

    @Column(name="news_id")
    private String id;

    public NewsId(String anId) {
        this();

        this.setId(anId);
    }

    public String id() {
        return this.id;
    }

    public NewsId(NewsId aNewsId) {
        this(aNewsId.id());
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            NewsId typedObject = (NewsId) anObject;
            equalObjects = typedObject.id().equals(this.id());
        }
        return equalObjects;
    }

    protected NewsId() {
        super();
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The News identity is required");

        this.id = id;
    }
}
