package by.com.lifetech.billingapi.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CacheService {
    Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    @Qualifier("defaultCacheManager")
    private CacheManager cacheManager;
    @Autowired
    @Qualifier("notExpireCacheManager")
    private CacheManager notExpireCacheManager;
    private final RedisTemplate<String, Object> redisTemplate;


    public void clearCaches() {
        logger.info("emptying dict caches");
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    public void clearSpecialCaches() {
        logger.info("emptying special caches");
        notExpireCacheManager.getCacheNames().forEach(cacheName -> notExpireCacheManager.getCache(cacheName).clear());
    }

    public Set<String> getKeys(String template) {
        return redisTemplate.keys(template);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public String getMemoryInfo() {
        return redisTemplate.getConnectionFactory()
                .getConnection()
                .info("memory").toString();
    }
}
