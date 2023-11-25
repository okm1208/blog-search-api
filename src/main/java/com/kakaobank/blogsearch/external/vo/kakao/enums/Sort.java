package com.kakaobank.blogsearch.external.vo.kakao.enums;

import com.kakaobank.blogsearch.common.enums.CommonSort;
import lombok.Getter;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@Getter
public enum Sort {

    ACCURACY("accuracy"), RECENCY("recency");

    final String parameterValue;

    Sort(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public static Sort convertSort(CommonSort commonSort) {
        if (commonSort == null) return null;

        if (commonSort == CommonSort.ACCURACY) {
            return ACCURACY;
        } else if (commonSort == CommonSort.RECENCY) {
            return RECENCY;
        }
        throw new RuntimeException("invalid sort enum value.");
    }
}

