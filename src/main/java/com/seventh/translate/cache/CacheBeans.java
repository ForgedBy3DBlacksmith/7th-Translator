package com.seventh.translate.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheBeans {
    @Bean
    public CacheStore<CachedMessage> messageCache() {
        return new CacheStore<CachedMessage>(1, TimeUnit.DAYS);
    }
}
