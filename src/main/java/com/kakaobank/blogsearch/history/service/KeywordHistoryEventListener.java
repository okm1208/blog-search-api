package com.kakaobank.blogsearch.history.service;

import com.kakaobank.blogsearch.data.entity.KeywordHistory;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryRepository;
import com.kakaobank.blogsearch.history.vo.KeywordSearchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/

@RequiredArgsConstructor
@Component
public class KeywordHistoryEventListener {

    private final KeywordHistoryRepository keywordHistoryRepository;

    // 비동기 처리시 주석 해제
    //@Async
    @EventListener
    @Transactional
    public void saveKeywordHistory(final KeywordSearchEvent keywordSearchEvent) {
        keywordHistoryRepository.save(new KeywordHistory(keywordSearchEvent.getKeyword()));
    }

}
