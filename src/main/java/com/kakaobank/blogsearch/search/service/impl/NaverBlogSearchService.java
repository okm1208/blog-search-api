package com.kakaobank.blogsearch.search.service.impl;

import com.kakaobank.blogsearch.common.exception.CustomBusinessException;
import com.kakaobank.blogsearch.external.impl.KakaoBlogSearchClient;
import com.kakaobank.blogsearch.external.impl.NaverBlogSearchClient;
import com.kakaobank.blogsearch.external.vo.kakao.request.KakaoBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import com.kakaobank.blogsearch.external.vo.naver.request.NaverBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.naver.response.NaverBlogSearchResponse;
import com.kakaobank.blogsearch.search.service.SearchApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Slf4j
@Service("naverBlogSearchService")
@RequiredArgsConstructor
public class NaverBlogSearchService implements SearchApiService<NaverBlogSearchRequest, NaverBlogSearchResponse> {

    private final NaverBlogSearchClient naverBlogSearchClient;

    @Override
    public NaverBlogSearchResponse search(final NaverBlogSearchRequest request) throws CustomBusinessException {
        return naverBlogSearchClient.searchBlog(request.getQuery(),
                request.getSort() != null ? request.getSort().getParameterValue() : null,
                request.getStart(),
                request.getDisplay());
    }

}
