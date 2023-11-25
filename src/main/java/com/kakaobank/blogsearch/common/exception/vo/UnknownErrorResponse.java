package com.kakaobank.blogsearch.common.exception.vo;

import org.springframework.http.HttpStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
public class UnknownErrorResponse implements ErrorResponse{
    private HttpStatus status;

    public UnknownErrorResponse(int status) {
        this.status = HttpStatus.valueOf(status);
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public int getErrorCode() {
        return DefaultErrorResponse.INTERNAL_SERVER_ERROR.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return DefaultErrorResponse.INTERNAL_SERVER_ERROR.getErrorMessage();
    }
}
