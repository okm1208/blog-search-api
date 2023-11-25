package com.kakaobank.blogsearch.history.service;

import com.kakaobank.blogsearch.data.entity.KeywordHistory;
import com.kakaobank.blogsearch.data.entity.KeywordHistoryStatistics;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryRepository;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : km.oh
 * @since : 2023/11/24
 * 블로그 검색시 저장 되어 있는 키워드 누적 카운트 값 배치 처리
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class KeywordHistorySummaryBatchJob {

    private final KeywordHistoryRepository keywordHistoryRepository;

    private final KeywordHistoryStatisticsRepository keywordHistoryStatisticsRepository;

    @Scheduled(cron = "0 0 */1 * * *")
    @Transactional
    public void keywordHistorySummaryBachJob() {
        LocalDateTime baseDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = baseDateTime.minusHours(1L) ;
        LocalDateTime endDateTime = baseDateTime;

        List<KeywordHistory> keywordHistoryList =
                keywordHistoryRepository.findAllByCreateDateTimeBetween(startDateTime, endDateTime);

        if (!CollectionUtils.isEmpty(keywordHistoryList)) {
            Map<String, Long> keywordCountMap =
                    keywordHistoryList.parallelStream().collect(
                            Collectors.groupingBy(keywordHistory ->
                                    keywordHistory.getKeyword(), Collectors.counting()
                            )
                    );
            keywordCountMap.entrySet().stream().forEach(keywordEntry -> {
                KeywordHistoryStatistics keywordHistoryStatistics =
                        new KeywordHistoryStatistics(keywordEntry.getKey(), keywordEntry.getValue(),
                                startDateTime.toLocalDate(), startDateTime.getHour());
                keywordHistoryStatisticsRepository.save(keywordHistoryStatistics);
            });
        }

    }

}
