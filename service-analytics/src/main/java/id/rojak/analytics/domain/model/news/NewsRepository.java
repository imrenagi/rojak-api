package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by imrenagi on 7/14/17.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByMediaId(MediaId mediaId);

}
