package com.itontheway.manage.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author xiegl
 * @Date 2022-6-13 16:16
 * @Desc caffeine cache config
 **/
@Configuration
public class CaffeineCacheConfig {

    @Value("${spring.cache.operate.log.time}")
    public  Long operateLogCacheTime;

    @Value("${spring.cache.operate.log.dict.time}")
    public  Long operateLogDictCacheTime;


    /**
     * @Author xiegl
     * @Date 2022-6-13 17:30
     * @param
     * @return org.springframework.cache.CacheManager
     * @desc 配置缓存管理器
     **/
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<>();
        Map<String, Object> map = getCacheType();
        for (String name : map.keySet()) {
            caches.add(new CaffeineCache(name, (Cache<Object, Object>) map.get(name)));
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }


    /**
     * @Author xiegl
     * @Date 2022-6-13 17:29
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @desc 初始化自定义缓存策略
     **/
    private  Map<String, Object> getCacheType() {
        Map<String, Object> map = new ConcurrentHashMap<>();

        // 日志字典缓存
        map.put("OperatorLogDictCache", Caffeine.newBuilder().recordStats()
                .expireAfterWrite(operateLogDictCacheTime, TimeUnit.SECONDS)
                .maximumSize(50)
                .build());

        // 日志缓存
        map.put("OperatorLogCache", Caffeine.newBuilder().recordStats()
                .expireAfterWrite(operateLogCacheTime, TimeUnit.SECONDS)
                .maximumSize(100)
                .build());

        // 用户角色
        map.put("UserRole", Caffeine.newBuilder().recordStats()
                .expireAfterWrite(operateLogCacheTime, TimeUnit.SECONDS)
                .maximumSize(100)
                .build());
        return map;
    }

}
