package com.kakaobank.blogsearch.common.exception.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomizableErrorResponse implements ErrorResponse{
    private HttpStatus status;
    private int errorCode;
    private String errorMessage;

    public static CustomizableErrorResponse of(Integer httpStatus, String errorMessage) {
        ErrorResponse errorResponse = ErrorResponse.of(httpStatus);

        return new CustomizableErrorResponse(errorResponse.getStatus(), errorResponse.getErrorCode(), errorMessage);
    }

    public static CustomizableErrorResponse of(DefaultErrorResponse errorResponse, String errorMessage) {
        return new CustomizableErrorResponse(errorResponse.getStatus(), errorResponse.getErrorCode(), errorMessage);
    }
}
