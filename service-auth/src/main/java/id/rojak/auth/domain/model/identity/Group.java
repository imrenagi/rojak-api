package id.rojak.auth.domain.model.identity;

import id.rojak.auth.common.domain.model.IdentifiedDomainObject;
import id.rojak.auth.domain.model.access.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by inagi on 8/1/17.
 */
@Entity
@Table(name = "tbl_group")
public class Group extends IdentifiedDomainObject {

    public static final String ROLE_GROUP_PREFIX = "ROLE-INTERNAL-GROUP: ";
    private static final long serialVersionUID = 1L;

    @Column(name="description")
    private String description;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "group", orphanRemoval = true)
    private Set<GroupMember> groupMembers;

    @Column(name="name")
    private String name;

    @Column(name="supports_nesting")
    private Boolean supportNesting;

    @ManyToOne
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private Role role;

    public Group(String aName, String aDescription) {
        this();

        this.setDescription(aDescription);
        this.setName(aName);
    }

    public Group(String aName,
                 String aDescription,
                 Boolean supportNesting,
                 Role aRole) {
        this(aName, aDescription);

        this.setRole(aRole);
        this.setSupportNesting(supportNesting);
    }

    protected Group() {
        super();

        this.setGroupMembers(new HashSet<GroupMember>(0));
    }

    public void addGroup(Group aGroup, GroupMemberService aGroupMemberService) {
        this.assertArgumentNotNull(aGroup, "Group must not be null.");
//        this.assertArgumentFalse(aGroupMemberService.isMemberGroup(aGroup, this.toGroupMember()), "Group recurrsion.");

//        if (this.groupMembers().add(aGroup.toGroupMember()) && !this.isInternalGroup()) {
//            DomainEventPublisher
//                    .instance()
//                    .publish(new GroupGroupAdded(
//                            this.tenantId(),
//                            this.name(),
//                            aGroup.name()));
//        }
    }

    public void addUser(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");
        this.assertArgumentTrue(aUser.isEnabled(), "User is not enabled.");

        if (this.groupMembers().add(aUser.toGroupMember()) && !this.isInternalGroup()) {
//            DomainEventPublisher
//                    .instance()
//                    .publish(new GroupUserAdded(
//                            this.tenantId(),
//                            this.name(),
//                            aUser.username()));
        }
    }

    public String description() {
        return this.description;
    }

    public Set<GroupMember> groupMembers() {
        return this.groupMembers;
    }

    public boolean isMember(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");
        this.assertArgumentTrue(aUser.isEnabled(), "User is not enabled.");

        boolean isMember =
                this.groupMembers().contains(aUser.toGroupMember());

        return isMember;
    }

    public String name() {
        return this.name;
    }

    public void removeGroup(Group aGroup) {
        this.assertArgumentNotNull(aGroup, "Group must not be null.");

        // not a nested remove, only direct member
//        if (this.groupMembers().remove(aGroup.toGroupMember()) && !this.isInternalGroup()) {
//            DomainEventPublisher
//                    .instance()
//                    .publish(new GroupGroupRemoved(
//                            this.tenantId(),
//                            this.name(),
//                            aGroup.name()));
//        }
    }

    public void removeUser(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");

        // not a nested remove, only direct member
        if (this.groupMembers().remove(aUser.toGroupMember()) && !this.isInternalGroup()) {
//            DomainEventPublisher
//                    .instance()
//                    .publish(new GroupUserRemoved(
//                            this.tenantId(),
//                            this.name(),
//                            aUser.username()));
        }
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            Group typedObject = (Group) anObject;
            equalObjects = this.name().equals(typedObject.name());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(2061 * 193)
                        + this.name().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "Group [description=" + description + ", name=" + name + "]";
    }

    protected void setDescription(String aDescription) {
        this.assertArgumentNotEmpty(aDescription, "Group description is required.");
        this.assertArgumentLength(aDescription, 1, 250, "Group description must be 250 characters or less.");

        this.description = aDescription;
    }

    protected void setGroupMembers(Set<GroupMember> aGroupMembers) {
        this.groupMembers = aGroupMembers;
    }

    protected boolean isInternalGroup() {
        return this.isInternalGroup(this.name());
    }

    protected boolean isInternalGroup(String aName) {
        return aName.startsWith(ROLE_GROUP_PREFIX);
    }

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Group name is required.");
        this.assertArgumentLength(aName, 1, 100, "Group name must be 100 characters or less.");

        if (this.isInternalGroup(aName)) {
            String uuid = aName.substring(ROLE_GROUP_PREFIX.length());

            try {
                UUID.fromString(uuid);
            } catch (Exception e) {
                throw new IllegalArgumentException("The group name has an invalid format.");
            }
        }

        this.name = aName;
    }

    protected GroupMember toGroupMember() {
        GroupMember groupMember =
                new GroupMember(
                        this.name(),
                        GroupMemberType.Group);

        return groupMember;
    }

    public Role role() {
        return this.role;
    }

    public void setRole(Role aRole) {
        this.assertArgumentNotNull(aRole, "Role is required");

        this.role = aRole;
    }

    public boolean isSupportNesting() {
        return this.supportNesting;
    }

    public void setSupportNesting(Boolean supportNesting) {
        this.supportNesting = supportNesting;
    }
}
