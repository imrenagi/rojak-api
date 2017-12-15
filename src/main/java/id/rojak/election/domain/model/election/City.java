package id.rojak.election.domain.model.election;

import id.rojak.common.domain.model.IdentifiedValueObject;

import javax.persistence.*;

/**
 * Created by imrenagi on 7/9/17.
 */
@Entity
@Table(name="tbl_cities")
public class City extends IdentifiedValueObject {

    @Column(name="name")
    private String name;

    @Embedded
    private Location location;

    @ManyToOne
    @JoinColumn(name="province_id", referencedColumnName = "id")
    private Province province;

    protected City() {
        super();
    }

    public City(String name,
                Location location,
                Province province) {
        this();
        this.setName(name);
        this.setLocation(location);
        this.setProvince(province);
    }

    public String name() {
        return this.name;
    }

    public void setName(String name) {
        this.assertArgumentNotEmpty(name, "Name is required and can't be empty");

        this.name = name;
    }

    public Location location() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.assertArgumentNotNull(location, "Location can't be null");

        this.location = location;
    }

    public Province province() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.assertArgumentNotNull(province, "Province can't be null");

        this.province = province;
    }
}
