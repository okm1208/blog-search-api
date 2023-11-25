package com.kakaobank.blogsearch.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordHistoryStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Long accumulateCount;

    @Column(nullable = false)
    private LocalDate searchDate;

    @Column(nullable = false)
    private int summaryHour;

    public KeywordHistoryStatistics(final String keyword,
                                    final Long accumulateCount,
                                    final LocalDate searchDate,
                                    final int summaryHour) {
        this.keyword = keyword;
        this.accumulateCount = accumulateCount;
        this.searchDate = searchDate;
        this.summaryHour = summaryHour;
    }

}
