package com.kakaobank.blogsearch.keyword.service;

import com.kakaobank.blogsearch.keyword.vo.KeywordRankDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class KeywordRankService {

    private final KeywordRankCacheService keywordRankCacheService;

    public List<KeywordRankDto> getTopSearchKeywordList(LocalDate searchDate, Pageable pageable) {
        return keywordRankCacheService.getTopSearchKeyword(searchDate, pageable);
    }

}
