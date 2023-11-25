package com.kakaobank.blogsearch.external.vo.naver.enums;

import com.kakaobank.blogsearch.common.enums.CommonSort;
import lombok.Getter;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Getter
public enum Sort {

    SIM("sim"), DATE("date");

    final String parameterValue;

    Sort(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public static com.kakaobank.blogsearch.external.vo.naver.enums.Sort convertSort(CommonSort commonSort) {
        if (commonSort == null) return null;

        if (commonSort == CommonSort.ACCURACY) {
            return SIM;
        } else if (commonSort == CommonSort.RECENCY) {
            return DATE;
        }
        throw new RuntimeException("invalid sort enum value.");
    }
}

