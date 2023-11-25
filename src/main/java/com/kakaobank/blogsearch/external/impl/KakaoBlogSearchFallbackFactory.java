package com.kakaobank.blogsearch.external.impl;

import com.kakaobank.blogsearch.common.exception.CustomBusinessException;
import com.kakaobank.blogsearch.common.exception.InternalServerException;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.ErrorResponse;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@RequiredArgsConstructor
@Component
public class KakaoBlogSearchFallbackFactory implements FallbackFactory<KakaoBlogSearchClient> {

    private final NaverBlogSearchClient naverBlogSearchClient;

    @Override
    public KakaoBlogSearchClient create(final Throwable cause) {
        if (cause instanceof InternalServerException || cause instanceof FeignException.FeignServerException) {
            return (query, sort, page, size) -> KakaoBlogSearchResponse.of(naverBlogSearchClient.searchBlog(query, sort, page, size));
        } else if (cause instanceof CustomBusinessException) {
            throw (CustomBusinessException) cause;
        }
        throw InternalServerException.of(DefaultErrorResponse.INTERNAL_SERVER_ERROR.getErrorMessage());
    }

}
