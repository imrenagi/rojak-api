package id.rojak.analytics.domain.model.media;

import org.springframework.stereotype.Repository;

/**
 * Created by imrenagi on 10/20/17.
 */
public interface MediaCacheRepository {
    void saveMedia(Media media);
    Media findMedia(MediaId mediaId);
}

