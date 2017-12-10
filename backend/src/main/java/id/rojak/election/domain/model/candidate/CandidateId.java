package id.rojak.election.domain.model.candidate;

import id.rojak.election.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by inagi on 7/4/17.
 */
@Embeddable
public class CandidateId extends ValueObject {

    @Column(name="candidate_id")
    private String id;

    protected CandidateId() {
        super();
    }

    public CandidateId(String anId) {
        this();

        this.setId(anId);
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The Candidate identity is required");

        this.id = id;
    }

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            CandidateId typedObject = (CandidateId) anObject;
            equalObjects = typedObject.id().equals(this.id());
        }
        return equalObjects;
    }
}