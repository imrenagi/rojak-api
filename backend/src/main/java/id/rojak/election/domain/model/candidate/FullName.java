package id.rojak.election.domain.model.candidate;



import id.rojak.common.AssertionConcern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by imrenagi on 7/6/17.
 */
@Embeddable
public class FullName extends AssertionConcern implements Serializable {

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    protected FullName() {
        super();
    }

    public FullName(String aFirstName, String aMiddleName, String aLastName) {
        super();

        this.setFirstName(aFirstName);
        this.setMiddleName(aMiddleName);
        this.setLastName(aLastName);
    }

    public FullName(FullName aFullName) {
        this(aFullName.firstName(), aFullName.middleName(), aFullName.lastName());
    }

    public String asFormattedName() {
        if (this.middleName().isEmpty()) {
            return String.format("%s %s", this.firstName(), this.lastName());
        } else {
            return String.format("%s %s %s", this.firstName(), this.middleName(), this.lastName());
        }
    }

    public FullName withChangedFirstName(String aFirstName) {
        return new FullName(aFirstName, this.middleName(), this.lastName());
    }

    public FullName withChangedMiddleName(String aMiddleName) {
        return new FullName(this.firstName(), aMiddleName, this.lastName());
    }

    public FullName withChangedLastName(String aLastName) {
        return new FullName(this.firstName(), this.middleName(), aLastName);
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            FullName typedObject = (FullName) anObject;
            equalObjects =
                    this.firstName().equals(typedObject.firstName()) &&
                            this.lastName().equals(typedObject.lastName()) &&
                            this.middleName().equals(typedObject.middleName());
        }

        return equalObjects;
    }

    public void setFirstName(String firstName) {
        this.assertArgumentNotEmpty(firstName, "First name is required.");
        this.assertArgumentLength(firstName, 1, 50, "First name must be 50 characters or less.");
        this.assertArgumentTrue(
                Pattern.matches("[A-Z][a-z]*", firstName),
                "First name must be at least one character in length, starting with a capital letter.");

        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.assertArgumentNotNull(middleName, "Middle name should not be null");

        if (!middleName.isEmpty()) {
            this.assertArgumentLength(middleName, 1, 50, "The Middle name must be 50 characters or less.");
            this.assertArgumentTrue(
                    Pattern.matches("^[a-zA-Z'][ a-zA-Z'-]*[a-zA-Z']?", middleName),
                    "Middle name must be at least one character in length.");
        }

        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.assertArgumentNotEmpty(lastName, "The last name is required.");
        this.assertArgumentLength(lastName, 1, 50, "The last name must be 50 characters or less.");
        this.assertArgumentTrue(
                Pattern.matches("^[a-zA-Z'][ a-zA-Z'-]*[a-zA-Z']?", lastName),
                "Last name must be at least one character in length.");

        this.lastName = lastName;
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String middleName() {
        return this.middleName;
    }
}
