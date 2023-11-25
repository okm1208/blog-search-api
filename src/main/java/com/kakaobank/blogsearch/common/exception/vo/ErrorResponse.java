package com.kakaobank.blogsearch.common.exception.vo;

import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
public interface ErrorResponse {

    HttpStatus getStatus();

    int getErrorCode();

    String getErrorMessage();

    static ErrorResponse of(int status) {
        Stream<ErrorResponse> stream = Stream.of(DefaultErrorResponse.values());

        return stream
                .filter(e -> e.getStatus().value() == status)
                .findFirst()
                .orElse(new UnknownErrorResponse(status));
    }
}
