package id.rojak.election.domain.model;

import id.rojak.election.common.AssertionConcern;

/**
 * Created by inagi on 7/3/17.
 */
public abstract class Entity extends AssertionConcern {

    private int concurrencyVersion;

    public Entity() {
        super();

        this.setConcurrencyVersion(0);
    }

    public int concurrencyVersion() {
        return this.concurrencyVersion;
    }

    public void setConcurrencyVersion(int aConcurrencyVersion) {
        this.concurrencyVersion = aConcurrencyVersion;
    }

}
