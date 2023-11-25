package com.kakaobank.blogsearch.search.service.impl;

import com.kakaobank.blogsearch.common.exception.CustomBusinessException;
import com.kakaobank.blogsearch.external.impl.KakaoBlogSearchClient;
import com.kakaobank.blogsearch.search.service.SearchApiService;
import com.kakaobank.blogsearch.external.vo.kakao.request.KakaoBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@Slf4j
@Service("kakaoBlogSearchService")
@RequiredArgsConstructor
public class KakaoBlogSearchService implements SearchApiService<KakaoBlogSearchRequest, KakaoBlogSearchResponse> {

    private final KakaoBlogSearchClient kakaoBlogSearchClient;

    @Override
    public KakaoBlogSearchResponse search(final KakaoBlogSearchRequest request) throws CustomBusinessException {
        return kakaoBlogSearchClient.searchBlog(request.getQuery(),
                request.getSort() != null ? request.getSort().getParameterValue() : null,
                request.getPage(),
                request.getSize());
    }

}
