package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorityException extends CustomBusinessException{
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.FORBIDDEN;

    public AuthorityException() {
        super(defaultErrorResponse);
    }

    private AuthorityException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static AuthorityException of(String message) {
        return new AuthorityException(message);
    }
}
