package com.kakaobank.blogsearch.search.service;

import com.kakaobank.blogsearch.common.exception.CustomBusinessException;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
public interface SearchApiService<T, U> {

    U search(T request) throws CustomBusinessException;
}
