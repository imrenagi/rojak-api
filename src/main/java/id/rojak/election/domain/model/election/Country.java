package id.rojak.election.domain.model.election;

import id.rojak.common.domain.model.IdentifiedValueObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by imrenagi on 7/9/17.
 */
@Entity
@Table(name="tbl_countries")
public class Country extends IdentifiedValueObject {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "country", orphanRemoval = true)
    private List<Province> provinces;

    protected Country() {
        super();
    }

    public Country(String aName, String aCode) {
        this();
        this.setName(aName);
        this.setCode(aCode);
    }

    public String name() {
        return this.name;
    }

    public void setName(String name) {
        this.assertArgumentNotEmpty(name, "Country name is required and can't be empty");

        this.name = name;
    }

    public String code() {
        return this.code;
    }

    public void setCode(String code) {
        this.assertArgumentNotEmpty(code, "Country code is required");
        this.assertArgumentLength(code, 1, 10, "Maximum length of code is 10");

        this.code = code;
    }

    public List<Province> provinces() {
        return provinces;
    }
}
