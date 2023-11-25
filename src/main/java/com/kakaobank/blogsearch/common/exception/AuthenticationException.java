package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends CustomBusinessException {
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.UNAUTHORIZED;


    public AuthenticationException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static AuthenticationException of(String message) {
        return new AuthenticationException(message);
    }
}
