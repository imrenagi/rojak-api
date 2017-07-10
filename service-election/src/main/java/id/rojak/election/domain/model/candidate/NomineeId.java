package id.rojak.election.domain.model.candidate;

import id.rojak.election.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by imrenagi on 7/6/17.
 */
@Embeddable
public class NomineeId extends ValueObject {

//    @Column(name="nominee_id", insertable = false, updatable = false)
    @Column(name="nominee_id")
    private String id;

    protected NomineeId() {
        super();
    }

    public NomineeId(String anId) {
        this();

        this.setId(anId);
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The Nominee identity is required");

        this.id = id;
    }

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            NomineeId typedObject = (NomineeId) anObject;
            equalObjects = typedObject.id().equals(this.id());
        }
        return equalObjects;
    }
}

