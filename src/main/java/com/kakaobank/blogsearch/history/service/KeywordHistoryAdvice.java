package com.kakaobank.blogsearch.history.service;

import com.kakaobank.blogsearch.external.vo.SearchRequest;
import com.kakaobank.blogsearch.history.vo.KeywordSearchEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class KeywordHistoryAdvice {

    private final ApplicationEventPublisher applicationEventPublisher;

    @After("execution(* com.kakaobank.blogsearch.search.service.SearchApiService.search(..)) && args(request)")
    public void historySaveJoinPoint(Object request) {

        if (request instanceof SearchRequest) {
            SearchRequest requestVo = (SearchRequest) request;
            if (StringUtils.hasText(requestVo.getKeyword())) {
                applicationEventPublisher.publishEvent(new KeywordSearchEvent(requestVo.getKeyword().trim()));
            }
        }

    }

}
