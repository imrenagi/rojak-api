package id.rojak.auth.domain.model.identity;

import id.rojak.auth.common.domain.model.IdentifiedDomainObject;
import id.rojak.auth.domain.model.DomainRegistry;

import javax.persistence.*;

/**
 * Created by inagi on 8/1/17.
 */
@Entity
@Table(name = "tbl_user")
public class User extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    @Embedded
    private Enablement enablement;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Person person;

    public User(
            String aUsername,
            String aPassword,
            Enablement anEnablement,
            Person aPerson) {

        this();

        this.setEnablement(anEnablement);
        this.setPerson(aPerson);
        this.setUsername(aUsername);

        this.protectPassword("", aPassword);

        aPerson.internalOnlySetUser(this);

//        DomainEventPublisher
//                .instance()
//                .publish(new UserRegistered(
//                        this.tenantId(),
//                        aUsername,
//                        aPerson.name(),
//                        aPerson.contactInformation().emailAddress()));
    }

    protected User() {
        super();
    }

    public void changePassword(String aCurrentPassword, String aChangedPassword) {
        this.assertArgumentNotEmpty(
                aCurrentPassword,
                "Current and new password must be provided.");

        this.assertArgumentEquals(
                this.password(),
                this.asEncryptedValue(aCurrentPassword),
                "Current password not confirmed.");

        this.protectPassword(aCurrentPassword, aChangedPassword);

//        DomainEventPublisher
//                .instance()
//                .publish(new UserPasswordChanged(
//                        this.tenantId(),
//                        this.username()));
    }

    public void changePersonalContactInformation(ContactInformation aContactInformation) {
        this.person().changeContactInformation(aContactInformation);
    }

    public void changePersonalName(FullName aPersonalName) {
        this.person().changeName(aPersonalName);
    }

    public void defineEnablement(Enablement anEnablement) {
        this.setEnablement(anEnablement);

//        DomainEventPublisher
//                .instance()
//                .publish(new UserEnablementChanged(
//                        this.tenantId(),
//                        this.username(),
//                        this.enablement()));
    }

    public boolean isEnabled() {
        return this.enablement().isEnablementEnabled();
    }

    public Person person() {
        return this.person;
    }

//    public UserDescriptor userDescriptor() {
//        return new UserDescriptor(
//                this.username(),
//                this.person().emailAddress().address());
//    }

    public String username() {
        return this.username;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            User typedObject = (User) anObject;
            equalObjects =
                    this.username().equals(typedObject.username());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(45217 * 269)
                        + this.username().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "User [username=" + username
                + ", person=" + person + ", enablement=" + enablement + "]";
    }

    protected String asEncryptedValue(String aPlainTextPassword) {
        String encryptedValue =
                DomainRegistry
                        .encryptionService()
                        .encryptedValue(aPlainTextPassword);
        System.out.println("Encrypted password is " + encryptedValue);
        return encryptedValue;
    }

    protected void assertPasswordsNotSame(String aCurrentPassword, String aChangedPassword) {
        this.assertArgumentNotEquals(
                aCurrentPassword,
                aChangedPassword,
                "The password is unchanged.");
    }

    protected void assertPasswordNotWeak(String aPlainTextPassword) {
        this.assertArgumentFalse(
                DomainRegistry.passwordService().isWeak(aPlainTextPassword),
                "The password must be stronger.");
    }

    protected void assertUsernamePasswordNotSame(String aPlainTextPassword) {
        this.assertArgumentNotEquals(
                this.username(),
                aPlainTextPassword,
                "The username and password must not be the same.");
    }

    protected Enablement enablement() {
        return this.enablement;
    }

    protected void setEnablement(Enablement anEnablement) {
        this.assertArgumentNotNull(anEnablement, "The enablement is required.");

        this.enablement = anEnablement;
    }

    public String internalAccessOnlyEncryptedPassword() {
        return this.password();
    }

    public String password() {
        return this.password;
    }

    protected void setPassword(String aPassword) {
        this.password = aPassword;
    }

    protected void setPerson(Person aPerson) {
        this.assertArgumentNotNull(aPerson, "The person is required.");

        this.person = aPerson;
    }

    protected void protectPassword(String aCurrentPassword, String aChangedPassword) {
        this.assertPasswordsNotSame(aCurrentPassword, aChangedPassword);

        this.assertPasswordNotWeak(aChangedPassword);

        this.assertUsernamePasswordNotSame(aChangedPassword);

        this.setPassword(this.asEncryptedValue(aChangedPassword));
    }

    protected GroupMember toGroupMember() {
        GroupMember groupMember =
                new GroupMember(this.username());

        return groupMember;
    }

    protected void setUsername(String aUsername) {
        this.assertArgumentNotEmpty(aUsername, "The username is required.");
        this.assertArgumentLength(aUsername, 3, 250, "The username must be 3 to 250 characters.");

        this.username = aUsername;
    }
}
