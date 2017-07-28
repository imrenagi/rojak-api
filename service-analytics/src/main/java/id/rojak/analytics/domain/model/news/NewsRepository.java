package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

/**
 * Created by imrenagi on 7/14/17.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findByMediaId(MediaId mediaId, Pageable pageRequest);

    Page<News> findByMediaIdAndElectionId(MediaId mediaId,
                                          ElectionId electionId,
                                          Pageable pageRequest);

//    Page<News> findByMediaIdAndElectionIdAndTimestampBetween(
//            MediaId mediaId,
//            ElectionId electionId,
//            Date fromDate, Date toDate);

    default String nextId() {
        return UUID.randomUUID().toString();
    }

}
