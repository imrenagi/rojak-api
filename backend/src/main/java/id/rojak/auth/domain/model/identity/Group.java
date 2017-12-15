package id.rojak.auth.domain.model.identity;

import id.rojak.common.domain.model.IdentifiedDomainObject;
import id.rojak.auth.domain.model.access.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by inagi on 8/1/17.
 */
@Entity
@Table(name = "tbl_group")
public class Group extends IdentifiedDomainObject {

    private static final Logger log = LoggerFactory.getLogger(Group.class);

    public static final String ROLE_GROUP_PREFIX = "ROLE-INTERNAL-GROUP: ";
    private static final long serialVersionUID = 1L;

    @Embedded
    private GroupId groupId;

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

    public Group(GroupId groupId,
                 String aName,
                 String aDescription,
                 Boolean supportNesting,
                 Role aRole) {
        this(aName, aDescription, supportNesting, aRole);

        this.setGroupId(groupId);
    }

    protected Group() {
        super();

        this.setGroupMembers(new HashSet<GroupMember>(0));
    }

    public void addUser(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");
        this.assertArgumentTrue(aUser.isEnabled(), "User is not enabled.");

        if (this.addUserToGroupMember(aUser)) {
            log.info("Successfully add {} to {}", aUser.username(), this.name());
//            DomainEventPublisher
//                    .instance()
//                    .publish(new GroupUserAdded(
//                            this.tenantId(),
//                            this.name(),
//                            aUser.username()));
        } else {
            log.info("Failed add {} to {}", aUser.username(), this.name());
        }
    }

    private boolean addUserToGroupMember(User user) {
        GroupMember groupMember = user.toGroupMember();

        groupMember.setGroup(this);
        return this.groupMembers().add(groupMember);
    }

    private boolean removeUserFromGroupMember(User user) {
        GroupMember groupMember = user.toGroupMember();

        groupMember.setGroup(this);
        return this.groupMembers().remove(groupMember);
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

    public void removeUser(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");

        // not a nested remove, only direct member
        if (this.removeUserFromGroupMember(aUser)) {
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

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Group name is required.");
        this.assertArgumentLength(aName, 1, 100, "Group name must be 100 characters or less.");

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

    public void setGroupId(GroupId groupId) {
        this.assertArgumentNotNull(groupId, "Group Id is required");
        this.assertArgumentNotEmpty(groupId.id(), "Group id must not be empty");

        this.groupId = groupId;
    }

    public GroupId groupId() {
        return this.groupId;
    }
}
