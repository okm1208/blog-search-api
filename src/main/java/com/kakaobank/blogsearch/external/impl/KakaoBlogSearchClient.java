package com.kakaobank.blogsearch.external.impl;

import com.kakaobank.blogsearch.common.config.kakao.KakaoApiFeignConfig;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@FeignClient(name = "KAKAO-BLOG-API-CLIENT",
        url = "${external.kakao.blog-search-base-url}",
        configuration = KakaoApiFeignConfig.class,
        fallbackFactory = KakaoBlogSearchFallbackFactory.class
)
public interface KakaoBlogSearchClient {

    @GetMapping("/v2/search/blog")
    KakaoBlogSearchResponse searchBlog(@RequestParam("query") String query,
                                       @RequestParam("sort") String sort,
                                       @RequestParam("page") int page,
                                       @RequestParam("size") int size);

}