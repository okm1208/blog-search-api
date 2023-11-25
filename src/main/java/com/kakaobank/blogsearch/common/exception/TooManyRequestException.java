package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class TooManyRequestException extends CustomBusinessException {
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.TOO_MANY_REQUESTS;

    public TooManyRequestException() {
        super(defaultErrorResponse);
    }

    private TooManyRequestException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static TooManyRequestException of(String message) {
        return new TooManyRequestException(message);
    }
}
