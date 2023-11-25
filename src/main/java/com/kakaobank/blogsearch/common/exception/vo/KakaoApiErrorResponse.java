package com.kakaobank.blogsearch.common.exception.vo;

import lombok.Getter;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@Getter
public class KakaoApiErrorResponse {

    private String errorType;

    private String message;
}
