package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by imrenagi on 7/16/17.
 */
@Repository
public interface NewsSentimentRepository extends JpaRepository<NewsSentiment, Long> {

    @Query("SELECT new id.rojak.analytics.domain.model.news.SentimentCount(electionId, candidateId, mediaId, " +
            "sentimentType, COUNT(sentimentType)) " +
            "FROM NewsSentiment " +
            "WHERE electionId = :electionId AND candidateId = :candidateId " +
            "GROUP BY 3, 4")
    List<SentimentCount> sentimentsGroupedByMediaAndSentiment(
            @Param("electionId") ElectionId electionId,
            @Param("candidateId") CandidateId candidateId);

    @Query("SELECT new id.rojak.analytics.domain.model.news.SentimentCount(electionId, candidateId, " +
            "cast(createdAt as date), sentimentType, COUNT(sentimentType)) " +
            "FROM NewsSentiment " +
            "WHERE electionId = :electionId AND candidateId = :candidateId " +
            "GROUP BY 3, 4)")
    List<SentimentCount> sentimentsGroupedByDateAndSentiment(
            @Param("electionId") ElectionId electionId,
            @Param("candidateId") CandidateId candidateId);

    @Query("SELECT new id.rojak.analytics.domain.model.news.SentimentCount(electionId, candidateId, " +
            "cast(createdAt as date), sentimentType, COUNT(sentimentType)) " +
            "FROM NewsSentiment " +
            "WHERE electionId = :electionId AND candidateId = :candidateId " +
            "AND createdAt >= :startDate and createdAt <= :endDate " +
            "GROUP BY 3, 4)")
    List<SentimentCount> sentimentsGroupedByDateAndSentiment(
            @Param("electionId") ElectionId electionId,
            @Param("candidateId") CandidateId candidateId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("SELECT new id.rojak.analytics.domain.model.news.SentimentCount(electionId, candidateId, " +
            "cast(createdAt as date), sentimentType, COUNT(sentimentType)) " +
            "FROM NewsSentiment " +
            "WHERE electionId = :electionId AND candidateId = :candidateId AND sentimentType = :sentimentType " +
            "AND createdAt >= :startDate and createdAt <= :endDate " +
            "GROUP BY 3, 4)")
    List<SentimentCount> sentimentsGroupedByDateAndSentiment(
            @Param("electionId") ElectionId electionId,
            @Param("candidateId") CandidateId candidateId,
            @Param("sentimentType") SentimentType sentimentType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("SELECT new id.rojak.analytics.domain.model.news.SentimentCount(electionId, candidateId, " +
            "mediaId, cast(createdAt as date), sentimentType, COUNT(sentimentType)) " +
            "FROM NewsSentiment " +
            "WHERE electionId = :electionId AND mediaId = :mediaId AND sentimentType = :sentimentType " +
            "AND createdAt >= :startDate and createdAt <= :endDate " +
            "GROUP BY 2, 4, 5 " +
            "ORDER BY 4")
    List<SentimentCount> sentimentsIn(
            @Param("electionId") ElectionId electionId,
            @Param("mediaId") MediaId mediaId,
            @Param("sentimentType") SentimentType sentimentType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}
