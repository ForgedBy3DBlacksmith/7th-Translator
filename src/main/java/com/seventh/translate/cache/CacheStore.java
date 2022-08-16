package com.seventh.translate.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CacheStore<T> {
    private Cache<String, T> cache;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CacheStore(int expiryDuration, TimeUnit timeUnit) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public T get(String key) {
        return cache.getIfPresent(key);
    }

    public void add(String key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
            logger.info("Record stored in "
                    + value.getClass().getSimpleName()
                    + " Cache with Key = " + key);
        }
    }

    public void invalidate(String key) {
        cache.invalidate(key);
        logger.info("Record removed from cache: " + key);
    }
}
