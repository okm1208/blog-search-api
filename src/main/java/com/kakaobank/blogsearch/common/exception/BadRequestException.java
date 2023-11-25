package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends CustomBusinessException{
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.BAD_REQUEST;

    public BadRequestException() {
        super(defaultErrorResponse);
    }

    private BadRequestException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static BadRequestException of(String message) {
        return new BadRequestException(message);
    }
}
