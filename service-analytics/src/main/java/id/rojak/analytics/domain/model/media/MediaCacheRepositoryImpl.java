package id.rojak.analytics.domain.model.media;

import id.rojak.analytics.common.repository.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by imrenagi on 10/20/17.
 */
@Repository
public class MediaCacheRepositoryImpl extends BaseCache implements MediaCacheRepository {

    private static final String KEY = "media";

    @Autowired
    public MediaCacheRepositoryImpl(RedisTemplate<String,Object> redisTemplate) {
        super(redisTemplate);
        redisTemplate.expire(KEY, 24, TimeUnit.HOURS);
    }

    @Override
    public void saveMedia(Media media) {
        this.hashOperations().put(KEY, media.mediaId().id(), media);
    }

    @Override
    public Media findMedia(MediaId mediaId) {
        return (Media) this.hashOperations().get(KEY, mediaId.id());
    }
}

