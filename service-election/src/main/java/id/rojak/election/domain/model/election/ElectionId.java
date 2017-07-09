package id.rojak.election.domain.model.election;

import id.rojak.election.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by inagi on 7/3/17.
 */
@Embeddable
public class ElectionId extends ValueObject {

    @Column(name="election_id", insertable = false, updatable = false)
    private String id;

    public ElectionId(String anId) {
        this();

        this.setId(anId);
    }

    public String id() {
        return this.id;
    }

    public ElectionId(ElectionId anElectionId) {
        this(anElectionId.id());
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            ElectionId typedObject = (ElectionId) anObject;
            equalObjects = typedObject.id().equals(this.id());
        }
        return equalObjects;
    }

    protected ElectionId() {
        super();
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The election identity is required");

        this.id = id;
    }
}
