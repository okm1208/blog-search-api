package com.kakaobank.blogsearch.keyword.vo;


import com.kakaobank.blogsearch.data.entity.KeywordHistoryStatistics;
import lombok.Getter;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Getter
public class KeywordRankDto {

    private int rank;

    private String keyword;

    private long accumulateCount;

    public KeywordRankDto(final int rank, final KeywordHistoryStatistics keywordHistoryStatistics){
        this.rank = rank;
        this.keyword = keywordHistoryStatistics.getKeyword();
        this.accumulateCount = keywordHistoryStatistics.getAccumulateCount();
    }

}
