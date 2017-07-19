package id.rojak.analytics.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewPostalAddressCommand {

    private String streetAddress;
    private String country;
    private String province;
    private String city;
    private String zipCode;

    protected NewPostalAddressCommand() {

    }

    public NewPostalAddressCommand(String streetAddress,
                                   String country,
                                   String province,
                                   String city,
                                   String zipCode) {
        this();
        this.streetAddress = streetAddress;
        this.country = country;
        this.province = province;
        this.city = city;
        this.zipCode = zipCode;
    }

    @JsonProperty("street_address")
    public String getStreetAddress() {
        return streetAddress;
    }

    @JsonProperty("street_address")
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("province")
    public String getProvince() {
        return province;
    }

    @JsonProperty("province")
    public void setProvince(String province) {
        this.province = province;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("zip_code")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty("zip_code")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
