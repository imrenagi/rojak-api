package id.rojak.auth.domain.model.identity;


import id.rojak.auth.common.AssertionConcern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by inagi on 8/1/17.
 */
@Embeddable
public class FullName extends AssertionConcern implements Serializable {

    @Column(name = "name_first_name")
    private String firstName;

    @Column(name = "name_last_name")
    private String lastName;

    protected FullName() {
        super();
    }

    public FullName(String aFirstName, String aLastName) {
        super();

        this.setFirstName(aFirstName);
        this.setLastName(aLastName);
    }

    public FullName(FullName aFullName) {
        this(aFullName.firstName(), aFullName.lastName());
    }

    public String asFormattedName() {
        return String.format("%s %s", this.firstName(), this.lastName());
    }

    public FullName withChangedFirstName(String aFirstName) {
        return new FullName(aFirstName, this.lastName());
    }

    public FullName withChangedLastName(String aLastName) {
        return new FullName(this.firstName(), aLastName);
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            FullName typedObject = (FullName) anObject;
            equalObjects =
                    this.firstName().equals(typedObject.firstName()) &&
                            this.lastName().equals(typedObject.lastName());
        }

        return equalObjects;
    }

    protected void setFirstName(String firstName) {
        this.assertArgumentNotEmpty(firstName, "First name is required.");
        this.assertArgumentLength(firstName, 1, 50, "First name must be 50 characters or less.");
        this.assertArgumentTrue(
                Pattern.matches("[A-Z][a-z]*", firstName),
                "First name must be at least one character in length, starting with a capital letter.");

        this.firstName = firstName;
    }


    protected void setLastName(String lastName) {
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
}
