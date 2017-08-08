package id.rojak.auth.domain.model.identity;

import id.rojak.auth.common.AssertionConcern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by inagi on 8/1/17.
 */
@Embeddable
public class PostalAddress extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "contact_information_postal_address_city")
    private String city;

    @Column(name = "contact_information_postal_address_country")
    private String country;

    @Column(name = "contact_information_postal_address_postal_code")
    private String postalCode;

    @Column(name = "contact_information_postal_address_state_province")
    private String stateProvince;

    @Column(name = "contact_information_postal_address_street_address")
    private String streetAddress;

    public PostalAddress(
            String aStreetAddress,
            String aCity,
            String aStateProvince,
            String aPostalCode,
            String aCountry) {

        super();

        this.setCity(aCity);
        this.setCountry(aCountry);
        this.setPostalCode(aPostalCode);
        this.setStateProvince(aStateProvince);
        this.setStreetAddress(aStreetAddress);
    }

    public PostalAddress(PostalAddress aPostalAddress) {
        this(aPostalAddress.streetAddress(),
                aPostalAddress.city(),
                aPostalAddress.stateProvince(),
                aPostalAddress.postalCode(),
                aPostalAddress.country());
    }

    public String city() {
        return this.city;
    }

    public String country() {
        return this.country;
    }

    public String postalCode() {
        return this.postalCode;
    }

    public String stateProvince() {
        return this.stateProvince;
    }

    public String streetAddress() {
        return this.streetAddress;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            PostalAddress typedObject = (PostalAddress) anObject;
            equalObjects =
                    this.streetAddress().equals(typedObject.streetAddress()) &&
                            this.city().equals(typedObject.city()) &&
                            this.stateProvince().equals(typedObject.stateProvince()) &&
                            this.postalCode().equals(typedObject.postalCode()) &&
                            this.country().equals(typedObject.country());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(31589 * 227)
                        + this.streetAddress().hashCode()
                        + this.city().hashCode()
                        + this.stateProvince().hashCode()
                        + this.postalCode().hashCode()
                        + this.country().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "PostalAddress [streetAddress=" + streetAddress
                + ", city=" + city + ", stateProvince=" + stateProvince
                + ", postalCode=" + postalCode
                + ", country=" + country + "]";
    }

    protected PostalAddress() {
        super();
    }

    private void setCity(String aCity) {
        this.assertArgumentNotEmpty(aCity, "The city is required.");
        this.assertArgumentLength(aCity, 1, 100, "The city must be 100 characters or less.");

        this.city = aCity;
    }

    private void setCountry(String aCountry) {
        this.assertArgumentNotEmpty(aCountry, "The country is required.");
        this.assertArgumentLength(aCountry, 1, 100, "The country code must be two characters.");

        this.country = aCountry;
    }

    private void setPostalCode(String aPostalCode) {
        this.assertArgumentNotEmpty(aPostalCode, "The postal code is required.");
        this.assertArgumentLength(aPostalCode, 5, 12, "The postal code must be 12 characters or less.");

        this.postalCode = aPostalCode;
    }

    private void setStateProvince(String aStateProvince) {
        this.assertArgumentNotEmpty(aStateProvince, "The state/province is required.");
        this.assertArgumentLength(aStateProvince, 2, 100, "The state/province must be 100 characters or less.");

        this.stateProvince = aStateProvince;
    }

    private void setStreetAddress(String aStreetAddress) {
        this.assertArgumentNotEmpty(aStreetAddress, "The street address is required.");
        this.assertArgumentLength(aStreetAddress, 1, 100, "The street address must be 100 characters or less.");

        this.streetAddress = aStreetAddress;
    }
}
