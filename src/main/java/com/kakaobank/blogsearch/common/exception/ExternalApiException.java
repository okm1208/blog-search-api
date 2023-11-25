package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.ErrorResponse;
import lombok.Getter;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
public class ExternalApiException extends RuntimeException{
    @Getter
    protected ErrorResponse errorResponse;

    public ExternalApiException(ErrorResponse errorResponse) {
        super(errorResponse.getErrorMessage());
        this.errorResponse = errorResponse;
    }
}
