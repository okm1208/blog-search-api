package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends CustomBusinessException{
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.NOT_FOUND;

    public NotFoundException() {
        super(defaultErrorResponse);
    }

    private NotFoundException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static NotFoundException of(String message) {
        return new NotFoundException(message);
    }
}
