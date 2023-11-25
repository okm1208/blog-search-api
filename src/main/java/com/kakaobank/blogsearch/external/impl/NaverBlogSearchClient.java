package com.kakaobank.blogsearch.external.impl;

import com.kakaobank.blogsearch.common.config.naver.NaverApiFeignConfig;
import com.kakaobank.blogsearch.external.vo.naver.response.NaverBlogSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@FeignClient(name = "NAVER-BLOG-API-CLIENT",
        url = "${external.naver.blog-search-base-url}",
        configuration = NaverApiFeignConfig.class
)
public interface NaverBlogSearchClient {

    @GetMapping("/v1/search/blog.json")
    NaverBlogSearchResponse searchBlog(@RequestParam("query") String query,
                                       @RequestParam("sort") String sort,
                                       @RequestParam("start") long start,
                                       @RequestParam("display") int display);

}
