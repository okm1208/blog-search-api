package com.kakaobank.blogsearch.common.enums;

/**
 * @author : km.oh
 * @since : 2023/11/25
 * 공통 정렬 enum
 **/
public enum CommonSort {
    ACCURACY("accuracy"),
    RECENCY("recency");

    final String parameterValue;

    CommonSort(String parameterValue) {
        this.parameterValue = parameterValue;
    }

}
