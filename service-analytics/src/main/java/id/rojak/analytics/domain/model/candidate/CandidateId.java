package id.rojak.analytics.domain.model.candidate;

import id.rojak.analytics.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by imrenagi on 7/16/17.
 */
@Embeddable
public class CandidateId extends ValueObject {

    @Column(name="candidate_id")
    private String id;

    public CandidateId(String anId) {
        this();

        this.setId(anId);
    }

    public String id() {
        return this.id;
    }

    public CandidateId(CandidateId candidateId) {
        this(candidateId.id());
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

    protected CandidateId() {
        super();
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The Candidate identity is required");

        this.id = id;
    }
}

