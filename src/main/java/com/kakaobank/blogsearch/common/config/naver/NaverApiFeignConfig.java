package com.kakaobank.blogsearch.common.config.naver;

import com.kakaobank.blogsearch.common.exception.handler.NaverApiErrorHandler;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@RequiredArgsConstructor
@Configuration
public class NaverApiFeignConfig {

    @Value("${external.naver.client-id}")
    private String clientId;

    @Value("${external.naver.client-secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor naverApiFeignRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", clientId);
            requestTemplate.header("X-Naver-Client-Secret", clientSecret);
        };
    }

    @Bean
    public ErrorDecoder naverApiFeignErrorDecoder() {
        return new NaverApiErrorHandler();
    }

}
