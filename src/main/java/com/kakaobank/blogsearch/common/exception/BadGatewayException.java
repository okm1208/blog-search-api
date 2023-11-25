package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends CustomBusinessException{
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.BAD_GATEWAY;

    public BadGatewayException() {
        super(defaultErrorResponse);
    }

    private BadGatewayException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static BadGatewayException of(String message) {
        return new BadGatewayException(message);
    }
}
