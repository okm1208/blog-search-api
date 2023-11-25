package com.kakaobank.blogsearch.keyword.service;

import com.kakaobank.blogsearch.common.exception.BadRequestException;
import com.kakaobank.blogsearch.common.exception.vo.DefaultErrorResponse;
import com.kakaobank.blogsearch.data.entity.KeywordHistoryStatistics;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryStatisticsRepository;
import com.kakaobank.blogsearch.keyword.vo.KeywordRankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kakaobank.blogsearch.common.config.LocalCacheConfig.TOP_SEARCH_QUERY_LIST_CACHE_KEY;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class KeywordRankCacheService {

    private final KeywordHistoryStatisticsRepository keywordHistoryStatisticsRepository;

    @Cacheable(value = TOP_SEARCH_QUERY_LIST_CACHE_KEY, key = "#searchDate.toString()", unless = "#result == null")
    public List<KeywordRankDto> getTopSearchKeyword(LocalDate searchDate, Pageable pageable) {

        List<KeywordHistoryStatistics> topKeywordHistoryStatisticsList =
                keywordHistoryStatisticsRepository.findAllBySearchDateAndSummaryHourOrderBySearchCountDesc(searchDate, pageable);

        if (topKeywordHistoryStatisticsList.isEmpty()) {
            throw BadRequestException.of(DefaultErrorResponse.DATA_NOT_FOUND.getErrorMessage());
        }
        return IntStream
                .range(0, topKeywordHistoryStatisticsList.size())
                .mapToObj(index -> new KeywordRankDto(index + 1, topKeywordHistoryStatisticsList.get(index)))
                .collect(Collectors.toList());
    }

}
