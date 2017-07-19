package id.rojak.analytics.domain.model.media;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by imrenagi on 7/14/17.
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    Media findByMediaId(MediaId mediaId);

    Page<Media> findAll(Pageable pageRequest);


}
