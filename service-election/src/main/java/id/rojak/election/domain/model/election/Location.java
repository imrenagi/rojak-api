package id.rojak.election.domain.model.election;

import id.rojak.election.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * Created by imrenagi on 7/9/17.
 */
@Embeddable
public class Location extends ValueObject {

    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

    protected Location() {
        super();
    }

    public Location(Double latitude, Double longitude) {
        this();
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    public Double latitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.assertArgumentRange(latitude, -90, 90,
                "Latitude value is out of bound");

        this.latitude = latitude;
    }

    public Double longitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.assertArgumentRange(longitude, -180, 180,
                "Longitude value is out of bound");
        this.longitude = longitude;
    }
}
