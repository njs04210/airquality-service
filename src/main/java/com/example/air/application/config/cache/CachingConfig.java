package com.example.air.application.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("airQualityInfo");
    }

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }

}
