package com.kakaobank.blogsearch.keyword.service;

import com.kakaobank.blogsearch.common.exception.BadRequestException;
import com.kakaobank.blogsearch.data.entity.KeywordHistoryStatistics;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryStatisticsRepository;
import com.kakaobank.blogsearch.keyword.vo.KeywordRankDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@SpringBootTest(properties = "spring.profiles.active=local")
class KeywordRankServiceTest {

    @Autowired
    private KeywordRankService keywordRankService;

    @MockBean
    private KeywordHistoryStatisticsRepository keywordHistoryStatisticsRepository;

    @Test
    public void keywordTopSearchOrderTest() {
        final LocalDate currentDate = LocalDate.now();
        final PageRequest pageable = PageRequest.of(0, 2);
        assertThrows(BadRequestException.class, () -> {
            keywordRankService.getTopSearchKeywordList(currentDate, pageable);
        });

        final LocalDate baseSearchDate = LocalDate.now();
        final int summaryHour = 9;
        when(keywordHistoryStatisticsRepository.findAllBySearchDateAndSummaryHourOrderBySearchCountDesc(currentDate, pageable))
                .thenReturn(List.of(
                        new KeywordHistoryStatistics("카카오뱅크", 214L, baseSearchDate, summaryHour),
                        new KeywordHistoryStatistics("대한민국", 100L, baseSearchDate, summaryHour),
                        new KeywordHistoryStatistics("세탁서비스", 5L, baseSearchDate, summaryHour)
                ));
        List<KeywordRankDto> keywordRankDtoList =
                keywordRankService.getTopSearchKeywordList(currentDate, pageable);

        assertThat(keywordRankDtoList, is(notNullValue()));
        assertThat(keywordRankDtoList.size(), is(3));
        assertThat(keywordRankDtoList.get(0).getKeyword(), is("카카오뱅크"));
        assertThat(keywordRankDtoList.get(keywordRankDtoList.size() - 1).getKeyword(), is("세탁서비스"));
    }

}