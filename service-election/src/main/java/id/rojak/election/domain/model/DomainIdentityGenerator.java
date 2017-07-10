package id.rojak.election.domain.model;

import java.util.UUID;

/**
 * Created by imrenagi on 7/9/17.
 */
public interface DomainIdentityGenerator {

    public default String nextId() {
        return UUID.randomUUID().toString();
    }
}
