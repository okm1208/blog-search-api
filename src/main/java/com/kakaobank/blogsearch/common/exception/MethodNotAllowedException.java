package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends CustomBusinessException{
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.METHOD_NOT_ALLOWED;

    private MethodNotAllowedException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static MethodNotAllowedException of(String message) {
        return new MethodNotAllowedException(message);
    }
}
