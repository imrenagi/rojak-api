package id.rojak.analytics.domain.model.sentiments;

/**
 * Created by inagi on 7/27/17.
 */
public interface ChartPoint<T, U> {

    T xValue();
    U yValue();

}
