package com.kakaobank.blogsearch.external.vo.kakao.pageable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@NoArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Meta {

    @JsonProperty("is_end")
    private boolean isEnd;

    private Integer pageableCount;

    private SameName sameName;

    private Long totalCount;

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class SameName {

        List<String> region;

        String keyword;

        String selectedRegion;

    }

    public Meta(final boolean isEnd, final Integer pageableCount, final Long totalCount){
        this.isEnd = isEnd;
        this.pageableCount = pageableCount;
        this.totalCount = totalCount;
    }


}
