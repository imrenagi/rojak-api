package id.rojak.auth.domain.model.identity;

import id.rojak.common.domain.model.IdentifiedValueObject;

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

    @Enumerated(EnumType.STRING)
    private GroupMemberType type;

    public boolean isGroup() {
        return type.isGroup();
    }

    public boolean isUser() {
        return type.isUser();
    }

    public String name() {
        return this.name;
    }

    public GroupMemberType type() {
        return this.type;
    }

    public Group group() { return this.group; }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            GroupMember typedObject = (GroupMember) anObject;
            equalObjects =
                            this.name().equals(typedObject.name()) &&
                            this.type().equals(typedObject.type());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                + (21941 * 197)
                        + this.type().hashCode()
                        + this.name().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "GroupMember [name=" + name + ", type=" + type + "]";
    }

    protected GroupMember(String aName, GroupMemberType aType) {
        this();

        this.setName(aName);
        this.setType(aType);
    }

//    protected GroupMember(String aName, Group aGroup, GroupMemberType aType) {
//        this();
//
//        this.setName(aName);
//        this.setType(aType);
//        this.setGroup(aGroup);
//    }

    protected GroupMember() {
        super();
    }

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Member name is required.");
        this.assertArgumentLength(aName, 1, 100, "Member name must be 100 characters or less.");

        this.name = aName;
    }

    protected void setType(GroupMemberType aType) {
        this.assertArgumentNotNull(aType, "The type must be provided.");

        this.type = aType;
    }

    protected void setGroup(Group aGroup) {
        this.assertArgumentNotNull(aGroup, "Group must be provided");

        this.group = aGroup;

    }

}
