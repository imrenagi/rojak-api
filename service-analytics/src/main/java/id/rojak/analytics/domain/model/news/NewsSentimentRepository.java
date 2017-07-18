package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by imrenagi on 7/16/17.
 */
@Repository
public interface NewsSentimentRepository extends JpaRepository<NewsSentiment, Long> {

    @Query("SELECT new id.rojak.analytics.domain.model.news.SentimentAggregate(electionId, candidateId, mediaId, " +
            "sentimentType, COUNT(sentimentType)) " +
            "FROM NewsSentiment " +
            "WHERE electionId = :electionId AND candidateId = :candidateId AND mediaId = :mediaId " +
            "GROUP BY 3, 4")
    List<SentimentAggregate> sentimentsGroupBySentimentAndMedia(
            @Param("electionId") ElectionId electionId,
            @Param("candidateId") CandidateId candidateId,
            @Param("mediaId") MediaId mediaId);
}
