package id.rojak.analytics.domain.model.media;



import id.rojak.analytics.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by imrenagi on 7/14/17.
 */
@Embeddable
public class MediaId extends ValueObject {

    @Column(name="media_id")
    private String id;

    protected MediaId() {
        super();
    }

    public MediaId(String anId) {
        this();

        this.setId(anId);
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The Media identity is required");

        this.id = id;
    }

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            MediaId typedObject = (MediaId) anObject;
            equalObjects = typedObject.id().equals(this.id());
        }
        return equalObjects;
    }
}