package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.ErrorResponse;
import lombok.Getter;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
public class CustomBusinessException extends RuntimeException{
    @Getter
    protected ErrorResponse errorResponse;

    public CustomBusinessException(ErrorResponse errorResponse) {
        super(errorResponse.getErrorMessage());
        this.errorResponse = errorResponse;
    }
}
