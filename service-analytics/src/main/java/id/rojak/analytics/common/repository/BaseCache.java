package id.rojak.analytics.common.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by imrenagi on 10/20/17.
 */
public class BaseCache {

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;


    public BaseCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    protected HashOperations hashOperations() {
        return this.hashOperations;
    }

}
