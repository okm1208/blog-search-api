package com.kakaobank.blogsearch.external.vo.kakao.request;

import com.kakaobank.blogsearch.external.vo.SearchRequest;
import com.kakaobank.blogsearch.external.vo.kakao.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KakaoBlogSearchRequest implements SearchRequest {

    private String query;

    private int page;

    private int size;

    private Sort sort;

    public KakaoBlogSearchRequest(final String query, final Sort sort, final Pageable pageable) {
        this.query = query;
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();
        this.sort = sort;
    }
    @Override
    public String getKeyword() {
        return query;
    }
}
