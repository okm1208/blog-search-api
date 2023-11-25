package com.kakaobank.blogsearch.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Configuration
@Slf4j
@EnableCaching
@EnableScheduling
public class LocalCacheConfig {

    public static final String TOP_SEARCH_QUERY_LIST_CACHE_KEY = "topSearchKeywordList";

    @CacheEvict(value = TOP_SEARCH_QUERY_LIST_CACHE_KEY, allEntries = true)
    @Scheduled(fixedDelayString = "${caching.spring.topSearchKeywordList}")
    public void clearTopSearchKeywordList() {
    }

}
