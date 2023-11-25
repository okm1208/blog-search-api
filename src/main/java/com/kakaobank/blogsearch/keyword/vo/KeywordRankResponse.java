package com.kakaobank.blogsearch.keyword.vo;

import lombok.Getter;

import java.util.List;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Getter
public class KeywordRankResponse {

    private List<KeywordRankDto> keywordRankDtoList;

    public KeywordRankResponse(final List<KeywordRankDto> keywordRankDtoList) {
        this.keywordRankDtoList = keywordRankDtoList;
    }

}
