package com.kakaobank.blogsearch.common.exception.handler;

import com.kakaobank.blogsearch.common.config.ExternalErrorMessageProperties;
import com.kakaobank.blogsearch.common.exception.*;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author : km.oh
 * @since : 2023/11/23
 * 카카오 API 연동 에러 헨들러
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoApiErrorHandler implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(final String methodKey, final Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        Exception exception = errorDecoder.decode(methodKey, response);

        log.error("kakao api call exception message: {}", exception.getMessage());
        log.error("{}", exception);
        if (exception instanceof FeignException) {
            FeignException feignException = (FeignException) exception;
            if (feignException.responseBody().isPresent()) {
                return convertCustomBusinessException(responseStatus);
            }
        }
        return exception;
    }

    private CustomBusinessException convertCustomBusinessException(final HttpStatus responseStatus) {
        if (responseStatus == HttpStatus.BAD_REQUEST) {
            return BadRequestException.of(ExternalErrorMessageProperties.KAKAO_API_BAD_REQUEST);
        } else if (responseStatus == HttpStatus.UNAUTHORIZED) {
            return AuthenticationException.of(ExternalErrorMessageProperties.KAKAO_API_UNAUTHORIZED);
        } else if (responseStatus == HttpStatus.FORBIDDEN) {
            return AuthorityException.of(ExternalErrorMessageProperties.KAKAO_API_FORBIDDEN);
        } else if (responseStatus == HttpStatus.TOO_MANY_REQUESTS) {
            return TooManyRequestException.of(ExternalErrorMessageProperties.KAKAO_TOO_MANY_REQUESTS);
        } else if (responseStatus == HttpStatus.BAD_GATEWAY) {
            return BadGatewayException.of(ExternalErrorMessageProperties.KAKAO_BAD_GATEWAY_REQUESTS);
        } else if (responseStatus == HttpStatus.SERVICE_UNAVAILABLE) {
            return ServiceUnavailableException.of(ExternalErrorMessageProperties.KAKAO_API_SERVICE_UNAVAILABLE);
        } else if (responseStatus == HttpStatus.NOT_FOUND) {
            return NotFoundException.of(ExternalErrorMessageProperties.KAKAO_API_NOT_FOUND);
        }


        return InternalServerException.of(ExternalErrorMessageProperties.KAKAO_API_INTERNAL_SERVER_ERROR);
    }

}
