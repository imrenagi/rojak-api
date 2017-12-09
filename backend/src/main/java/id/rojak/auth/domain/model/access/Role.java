package id.rojak.auth.domain.model.access;

import id.rojak.auth.common.domain.model.IdentifiedDomainObject;
import id.rojak.auth.domain.model.identity.Group;
import id.rojak.auth.domain.model.identity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by inagi on 8/1/17.
 */
@Entity
@Table(name="tbl_role")
public class Role extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @JoinTable(name = "tbl_role_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @ManyToMany
    private Set<Permission> permissions;

    public Role(
            String aName,
            String aDescription) {

        this.setDescription(aDescription);
        this.setName(aName);
        this.initPermissions();

//        this.createInternalGroup(); //TODO need this?
    }

    private void initPermissions() {
        this.setPermissions(new HashSet<>(0));
    }

    public void assignUser(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");

//        this.group().addUser(aUser);

        // NOTE: Consider what a consuming Bounded Context would
        // need to do if this event was not enriched with the
        // last three user person properties. (Hint: A lot.)
//        DomainEventPublisher
//                .instance()
//                .publish(new UserAssignedToRole(
//                        this.tenantId(),
//                        this.name(),
//                        aUser.username(),
//                        aUser.person().name().firstName(),
//                        aUser.person().name().lastName(),
//                        aUser.person().emailAddress().address()));
    }

    public String description() {
        return this.description;
    }

//    public boolean isInRole(User aUser, GroupMemberService aGroupMemberService) {
//        return this.group().isMember(aUser, aGroupMemberService);
//    }

    public String name() {
        return this.name;
    }

    public void unassignUser(User aUser) {
        this.assertArgumentNotNull(aUser, "User must not be null.");

//        this.group().removeUser(aUser);

//        DomainEventPublisher
//                .instance()
//                .publish(new UserUnassignedFromRole(
//                        this.tenantId(),
//                        this.name(),
//                        aUser.username()));
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            Role typedObject = (Role) anObject;
            equalObjects = this.name().equals(typedObject.name());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                + (18723 * 233)
                        + this.name().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "Role [name=" + name
                + ", description=" + description + "]";
    }

    protected Role() {
        super();
    }

//    protected void createInternalGroup() {
//        String groupName =
//                Group.ROLE_GROUP_PREFIX
//                        + UUID.randomUUID().toString().toUpperCase();
//
//        this.setGroup(new Group(
//                groupName,
//                "Role backing group for: " + this.name()));
//    }

    protected void setDescription(String aDescription) {
        this.assertArgumentNotEmpty(aDescription, "Role description is required.");
        this.assertArgumentLength(aDescription, 1, 250, "Role description must be 250 characters or less.");

        this.description = aDescription;
    }

//    protected Group group() {
//        return this.group;
//    }
//
//    protected void setGroup(Group aGroup) {
//        this.group = aGroup;
//    }

    protected void setName(String aName) {
        this.assertArgumentNotEmpty(aName, "Role name must be provided.");
        this.assertArgumentLength(aName, 1, 250, "Role name must be 100 characters or less.");

        String roleUpperCase = aName.trim().replaceAll("\\s+", "_").toUpperCase();

        this.name = ROLE_PREFIX.concat(roleUpperCase);;
    }

    public void assignTo(Group group) {
        this.assertArgumentNotNull(group, "Group should not be null!");

        group.setRole(this);
    }

    public void addPermission(Permission permission) {
        this.assertArgumentNotNull(permission, "Permission is required");

        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.assertArgumentNotNull(permission, "Permission is required");

        this.permissions.remove(permission);
    }

    protected void setPermissions(Set<Permission> permissions) {
        this.assertArgumentNotNull(permissions, "Permission is required");

        this.permissions = permissions;
    }

    public Set<Permission> permissions() {
        return this.permissions;
    }

}
