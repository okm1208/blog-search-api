package com.kakaobank.blogsearch.history.vo;

import lombok.Getter;


/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@Getter
public class KeywordSearchEvent {

    private String keyword;

    public KeywordSearchEvent(final String keyword) {
        this.keyword = keyword;
    }

}
