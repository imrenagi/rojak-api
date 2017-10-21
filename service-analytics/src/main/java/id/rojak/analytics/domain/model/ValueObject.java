package id.rojak.analytics.domain.model;


import id.rojak.analytics.common.AssertionConcern;

import java.io.Serializable;

/**
 * Created by inagi on 7/3/17.
 */
public abstract class ValueObject extends AssertionConcern implements Serializable {

    public ValueObject() {
        super();
    }
}
