package com.kakaobank.blogsearch.data.repository;

import com.kakaobank.blogsearch.data.entity.KeywordHistoryStatistics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
public interface KeywordHistoryStatisticsRepository extends JpaRepository<KeywordHistoryStatistics, Long> {

    @Query("SELECT khs , sum(khs.accumulateCount) as totalSearchCount " +
            "FROM KeywordHistoryStatistics khs " +
            "WHERE khs.searchDate = :searchDate " +
            "GROUP BY khs.keyword " +
            "ORDER BY totalSearchCount DESC ")
    List<KeywordHistoryStatistics> findAllBySearchDateAndSummaryHourOrderBySearchCountDesc(LocalDate searchDate, Pageable pageable);

}
