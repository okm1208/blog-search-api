package com.kakaobank.blogsearch.external.vo.naver.request;

import com.kakaobank.blogsearch.external.vo.SearchRequest;
import com.kakaobank.blogsearch.external.vo.naver.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NaverBlogSearchRequest implements SearchRequest {

    private String query;

    private long start;

    private int display;

    private Sort sort;

    public NaverBlogSearchRequest(final String query, final Sort sort, final Pageable pageable) {
        this.query = query;
        this.start = pageable.getOffset() == 0 ? 1 : pageable.getOffset();
        this.display = pageable.getPageSize();
        System.out.println("page size : "+pageable.getPageSize());
        System.out.println("page offset : "+pageable.getOffset());
        System.out.println("page number : "+pageable.getPageNumber());
        this.sort = sort;
    }

    @Override
    public String getKeyword() {
        return query;
    }

}
