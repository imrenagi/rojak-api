package id.rojak.auth.domain.model.identity;



import id.rojak.auth.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by inagi on 8/6/17.
 */
@Embeddable
public class GroupId extends ValueObject {

    @Column(name="group_id")
    private String id;

    public GroupId(String anId) {
        this();

        this.setId(anId);
    }

    public String id() {
        return this.id;
    }

    public GroupId(GroupId aGroupId) {
        this(aGroupId.id());
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            GroupId typedObject = (GroupId) anObject;
            equalObjects = typedObject.id().equals(this.id());
        }
        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                + (2508 * 5)
                        + this.id().hashCode();

        return hashCodeValue;
    }

    protected GroupId() {
        super();
    }

    public void setId(String id) {
        this.assertArgumentNotNull(id, "The Group identity is required");

        this.id = id;
    }
}

