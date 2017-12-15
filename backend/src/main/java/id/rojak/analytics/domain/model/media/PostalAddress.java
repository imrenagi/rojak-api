package id.rojak.analytics.domain.model.media;


import id.rojak.common.domain.model.IdentifiedValueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by imrenagi on 7/14/17.
 */
@Entity
@Table(name="tbl_postal_address")
public class PostalAddress extends IdentifiedValueObject {

    @Column(name="street_address")
    private String streetAddress;

    @Column(name="country")
    private String country;

    @Column(name="province")
    private String province;

    @Column(name="city")
    private String city;

    @Column(name="zip_code")
    private String zipCode;

    protected PostalAddress() {

    }

    public PostalAddress(String streetAddress,
                         String country,
                         String province,
                         String city,
                         String zipCode) {
        this();
        this.setStreetAddress(streetAddress);
        this.setCountry(country);
        this.setProvince(province);
        this.setCity(city);
        this.setZipCode(zipCode);

    }

    public String streetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String country() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String province() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String city() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO implement this
        return super.equals(obj);
    }
}
