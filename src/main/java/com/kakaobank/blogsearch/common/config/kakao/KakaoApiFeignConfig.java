package com.kakaobank.blogsearch.common.config.kakao;

import com.kakaobank.blogsearch.common.exception.handler.KakaoApiErrorHandler;
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
public class KakaoApiFeignConfig {

    @Value("${external.kakao.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor kakaoApiFeignRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", apiKey);
        };
    }

    @Bean
    public ErrorDecoder kakaoApiFeignErrorDecoder() {
        return new KakaoApiErrorHandler();
    }

}
