package com.kakaobank.blogsearch.common.exception.handler;

import com.kakaobank.blogsearch.common.exception.CustomBusinessException;
import com.kakaobank.blogsearch.common.exception.vo.CommonResponse;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import com.kakaobank.blogsearch.common.exception.vo.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * @author : km.oh
 * @since : 2023/11/24
 * 검색 API 공통 에러 헨들러
 **/
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<CommonResponse<Void>> customBusinessExceptionHandler(CustomBusinessException e) {

        ErrorResponse errorResponse = e.getErrorResponse();
        return new ResponseEntity<>(
                CommonResponse.fail(
                        errorResponse.getErrorCode(),
                        errorResponse.getErrorMessage()),
                errorResponse.getStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> exceptionHandler(Exception e) {

        log.error("exception occurred :{}", e.getMessage());
        log.error("{}", e);

        ErrorResponse errorResponse = findCommonErrorCodeByDefaultException(e);
        return new ResponseEntity<>(CommonResponse.fail(
                errorResponse.getErrorCode(),
                errorResponse.getErrorMessage()
        ), errorResponse.getStatus());
    }

    protected ErrorResponse findCommonErrorCodeByDefaultException(Throwable error) {

        if (error instanceof MethodArgumentNotValidException
                || error instanceof MissingServletRequestParameterException
                || error instanceof MissingServletRequestPartException
                || error instanceof TypeMismatchException
                || error instanceof HttpMessageNotReadableException
                || error instanceof ConstraintViolationException
                || error instanceof ServletRequestBindingException
                || error instanceof BindException) {

            return DefaultErrorResponse.BAD_REQUEST;
        } else if (error instanceof NoHandlerFoundException) {
            return DefaultErrorResponse.NOT_FOUND;
        } else if (error instanceof HttpRequestMethodNotSupportedException) {
            return DefaultErrorResponse.METHOD_NOT_ALLOWED;
        } else if (error instanceof HttpMediaTypeNotAcceptableException) {
            return DefaultErrorResponse.NOT_ACCEPTABLE;
        } else if (error instanceof HttpMediaTypeNotSupportedException) {
            return DefaultErrorResponse.UNSUPPORTED_MEDIA_TYPE;
        } else {
            return DefaultErrorResponse.INTERNAL_SERVER_ERROR;
        }
    }

}
