package id.rojak.election.common.domain.model;

import id.rojak.election.common.AssertionConcern;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by inagi on 7/3/17.
 */
@MappedSuperclass
public class IdentifiedDomainObject extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    protected IdentifiedDomainObject() {
        super();

        this.setId(-1);
    }

    protected long id() {
        return this.id;
    }

    private void setId(long anId) {
        this.id = anId;
    }

}
