package id.rojak.auth.domain.model.identity;

import id.rojak.auth.common.domain.model.IdentifiedValueObject;

import javax.persistence.*;

/**
 * Created by inagi on 8/1/17.
 */
@Entity
@Table(name = "tbl_group_member")
public class GroupMember extends IdentifiedValueObject {

    private static final long serialVersionUID = 1L;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Group group;

    public String name() {
        return this.name;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            GroupMember typedObject = (GroupMember) anObject;
            equalObjects = this.name().equals(typedObject.name());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                + (21941 * 197)
                        + this.name().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "GroupMember [name=" + name + "]";
    }

    protected GroupMember(String aName) {
        this();

        this.setName(aName);
    }

    protected GroupMember() {
        super();
    }

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Member name is required.");
        this.assertArgumentLength(aName, 1, 100, "Member name must be 100 characters or less.");

        this.name = aName;
    }

}
