package id.rojak.election.domain.model.election;

import id.rojak.election.common.domain.model.IdentifiedDomainObject;
import id.rojak.election.common.domain.model.IdentifiedValueObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by imrenagi on 7/9/17.
 */
@Entity
@Table(name="tbl_provinces")
public class Province extends IdentifiedValueObject {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "province", orphanRemoval = true)
    private List<City> cities;

    protected Province() {
        super();
    }

    public Province(String aName, String aCode, Country aCountry) {
        this();
        this.setName(aName);
        this.setCode(aCode);
    }

    public String name() {
        return this.name;
    }

    public void setName(String name) {
        this.assertArgumentNotEmpty(name, "Province name is required and can't be empty");

        this.name = name;
    }

    public String code() {
        return this.code;
    }

    public void setCode(String code) {
        this.assertArgumentNotEmpty(code, "Province code is required");
        this.assertArgumentLength(code, 1, 10, "Maximum length of code is 10");

        this.code = code;
    }

    public Country country() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.assertArgumentNotNull(country, "Country can't be null");

        this.country = country;
    }

    public List<City> cities() {
        return this.cities;
    }

    public void setCities(List<City> cities) {
        this.assertArgumentNotNull(cities, "City must not be null");

        this.cities = cities;
    }


}
