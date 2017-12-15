package id.rojak.auth.domain.model.identity;

import id.rojak.common.domain.model.IdentifiedValueObject;

import javax.persistence.*;

/**
 * Created by inagi on 8/1/17.
 */
@Entity
@Table(name = "tbl_person")
public class Person extends IdentifiedValueObject {

    @Embedded
    private ContactInformation contactInformation;

    @Embedded
    private FullName name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Person(
            FullName aName,
            ContactInformation aContactInformation) {

        this();

        this.setContactInformation(aContactInformation);
        this.setName(aName);
    }

    protected Person() {
        super();
    }

    public void changeContactInformation(ContactInformation aContactInformation) {
        this.setContactInformation(aContactInformation);

//        DomainEventPublisher
//                .instance()
//                .publish(new PersonContactInformationChanged(
//                        this.tenantId(),
//                        this.user().username(),
//                        this.contactInformation()));
    }

    public void changeName(FullName aName) {
        this.setName(aName);

//        DomainEventPublisher
//                .instance()
//                .publish(new PersonNameChanged(
//                        this.tenantId(),
//                        this.user().username(),
//                        this.name()));
    }

    public ContactInformation contactInformation() {
        return this.contactInformation;
    }

    public EmailAddress emailAddress() {
        return this.contactInformation().emailAddress();
    }

    public FullName name() {
        return this.name;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            Person typedObject = (Person) anObject;
            equalObjects =
                    this.user().username().equals(typedObject.user().username());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(90113 * 223)
                        + this.user().username().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", contactInformation=" + contactInformation + "]";
    }

    protected void setContactInformation(ContactInformation aContactInformation) {
        this.assertArgumentNotNull(aContactInformation, "The person contact information is required.");

        this.contactInformation = aContactInformation;
    }

    protected void setName(FullName aName) {
        this.assertArgumentNotNull(aName, "The person name is required.");

        this.name = aName;
    }

    protected User user() {
        return this.user;
    }

    public void internalOnlySetUser(User aUser) {
        this.user = aUser;
    }
}
