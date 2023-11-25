package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.exception.vo.CustomizableErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends CustomBusinessException {
    private static final DefaultErrorResponse defaultErrorResponse = DefaultErrorResponse.SERVICE_UNAVAILABLE;

    public ServiceUnavailableException() {
        super(defaultErrorResponse);
    }

    private ServiceUnavailableException(String message) {
        super(CustomizableErrorResponse.of(defaultErrorResponse, message));
    }

    public static ServiceUnavailableException of(String message) {
        return new ServiceUnavailableException(message);
    }
}
