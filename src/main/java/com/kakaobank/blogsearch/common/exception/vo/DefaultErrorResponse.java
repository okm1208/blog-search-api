package com.kakaobank.blogsearch.common.exception.vo;

import org.springframework.http.HttpStatus;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
public enum DefaultErrorResponse implements ErrorResponse{
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 1, "유효하지 않은 파라미티 입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 2, "인증이 필요한 서비스 입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 3, "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 4, "존재 하지 않는 주소 입니다."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, 5, "조회 하려는 데이터가 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 6, "허용되지 않는 메서드 타입 입니다."),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, 7, "접근을 허용하지 않습니다."),
    INVALID(HttpStatus.CONFLICT, 8, "유효 하지 않은 요청입니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 9, "혀용되지 않는 요청입니다."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS , 10, "호출 한도 초과 오류"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY,11 , "시스템 오류 입니다."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, 12, "서비스 점검 중 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 9999, "일시 적인 오류가 발생 하였습니다. 잠시 후 다시 시도 해 주세요.");

    final HttpStatus status;
    final int errorCode;
    final String errorMessage;

    DefaultErrorResponse(HttpStatus status, int errorCode, String errorMessage){
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
