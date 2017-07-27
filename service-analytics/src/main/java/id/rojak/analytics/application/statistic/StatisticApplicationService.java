package id.rojak.analytics.application.statistic;

import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.news.SentimentCount;
import id.rojak.analytics.domain.model.sentiments.ChartPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by inagi on 7/26/17.
 */
public class StatisticApplicationService {

    public List<Long> fillEmptyGapFor(List<Date> dateSeries,
                                      List<? extends ChartPoint<Date, Long>> dataSeries) {

        List<Long> values =
                new ArrayList<>(Collections.nCopies(dateSeries.size(), 0L));

        int countIdx = 0;

        for (int i = 0; i < values.size() && countIdx < dataSeries.size(); i++) {
            ChartPoint<Date, Long> data =
                    dataSeries.get(countIdx);

            if (DateHelper.isSimilar(dateSeries.get(i), data.xValue())) {
                values.set(i, data.yValue());
                countIdx++;
            }
        }

        return values;
    }
}
