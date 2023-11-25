package com.kakaobank.blogsearch.history.service;

import com.kakaobank.blogsearch.external.vo.kakao.enums.Sort;
import com.kakaobank.blogsearch.external.vo.kakao.request.KakaoBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import com.kakaobank.blogsearch.data.entity.KeywordHistoryStatistics;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryStatisticsRepository;
import com.kakaobank.blogsearch.search.service.SearchApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@SpringBootTest(properties = "spring.profiles.active=local")
class KeywordHistorySummaryBatchJobTest {

    @Autowired
    @Qualifier("kakaoBlogSearchService")
    private SearchApiService<KakaoBlogSearchRequest, KakaoBlogSearchResponse> kakaoBlogSearchService;

    @Autowired
    private KeywordHistorySummaryBatchJob keywordHistorySummaryBatchJob;

    @Autowired
    private KeywordHistoryStatisticsRepository keywordHistoryStatisticsRepository;

    @Test
    public void keywordHistorySummaryTest() {
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("국민은행")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("하나은행")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("하나은행")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("카카오뱅크")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("카카오뱅크")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());

        // 배치 실행
        keywordHistorySummaryBatchJob.keywordHistorySummaryBachJob();

        // 배치 결과 조회
        List<KeywordHistoryStatistics> keywordHistoryStatisticsList = keywordHistoryStatisticsRepository.findAll();

        assertNotNull(keywordHistoryStatisticsList);

        KeywordHistoryStatistics kbBankHistoryStatics = keywordHistoryStatisticsList.stream().filter(v -> v.getKeyword().equals("국민은행")).findFirst().orElse(null);
        assertThat(kbBankHistoryStatics.getAccumulateCount(), is(1L));
        KeywordHistoryStatistics hanaBankHistoryStatics = keywordHistoryStatisticsList.stream().filter(v -> v.getKeyword().equals("하나은행")).findFirst().orElse(null);
        assertThat(hanaBankHistoryStatics.getAccumulateCount(), is(2L));
        KeywordHistoryStatistics kakaoBankHistoryStatics = keywordHistoryStatisticsList.stream().filter(v -> v.getKeyword().equals("카카오뱅크")).findFirst().orElse(null);
        assertThat(kakaoBankHistoryStatics.getAccumulateCount(), is(2L));


    }

}