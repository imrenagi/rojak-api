package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.candidate.CandidateId;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by inagi on 7/31/17.
 */
public class BasicTopCandidateCalculator implements TopCandidateCalculator {

    @Override
    public CandidateId topCandidateFrom(Collection<CandidateNewsCount> candidateNewsCounts) {

        Queue<CandidateNewsCount> queue =
                new PriorityQueue<>((o1, o2) -> score(o2).compareTo(score(o1)));

        for (CandidateNewsCount count : candidateNewsCounts) {
            queue.add(count);
        }

        return (queue.isEmpty()) ? null : queue.poll().getCandidateId();
    }

    private Double score(NewsSentimentCount count) {
        if (count.totalPositiveNews() == 0) return 0.0;

        return (count.totalPositiveNews() - count.totalNegativeNews()) * 1.0 / count.totalPositiveNews();
    }

}
