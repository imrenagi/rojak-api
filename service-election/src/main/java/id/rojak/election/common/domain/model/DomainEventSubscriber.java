package id.rojak.election.common.domain.model;

/**
 * Created by inagi on 7/3/17.
 */
public interface DomainEventSubscriber<T> {

    public void handleEvent(final T aDomainEvent);

    public Class<T> subscribedToEventType();

}
