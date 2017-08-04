package id.rojak.auth.domain.model.access;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.auth.common.domain.model.IdentifiedDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by inagi on 8/2/17.
 */
@Entity
@Table(name="tbl_permissions")
public class Permission extends IdentifiedDomainObject {


    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="created_at",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    @Column(name="updatedAt",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedDate;

    protected Permission() {}

    public Permission(String aName,
                      String aDescription) {

        this();

        this.setName(aName);
        this.setDescription(aDescription);

        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());

        //TODO send domain item if necessary
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public void setName(String name) {
        this.assertArgumentNotNull(name, "Name is required!");
        this.assertArgumentNotEmpty(name, "Name can't be empty");

        this.name = name.trim().replaceAll("\\s+", "_").toUpperCase();
    }

    public void setDescription(String description) {
        this.assertArgumentNotNull(description, "Description is required!");
        this.assertArgumentNotEmpty(description, "Description can't be empty!");

        this.description = description;
    }

    public void setCreatedDate(Date createdDate) {
        this.assertArgumentNotNull(createdDate, "Created date is required");

        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.assertArgumentNotNull(createdDate, "Update date is required");

        this.updatedDate = updatedDate;
    }
}
