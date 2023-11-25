package com.kakaobank.blogsearch.data.dto;

import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import com.kakaobank.blogsearch.external.vo.naver.response.NaverBlogSearchResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@Getter
public class PaginationDto {

    private Page page;

    private List<BlogDto> itemList = new ArrayList<>();

    public PaginationDto(KakaoBlogSearchResponse kakaoBlogSearchResponse) {
        if (kakaoBlogSearchResponse != null) {
            if (kakaoBlogSearchResponse.getMeta() != null) {
                this.page = new Page(kakaoBlogSearchResponse.getMeta().isEnd(), kakaoBlogSearchResponse.getMeta().getTotalCount());
            }
            if (kakaoBlogSearchResponse.getDocuments() != null) {
                this.itemList = kakaoBlogSearchResponse.getDocuments()
                        .stream()
                        .map(v -> new BlogDto(v))
                        .collect(Collectors.toList());
            }
        }
    }

    public PaginationDto(NaverBlogSearchResponse naverBlogSearchResponse) {
        if (naverBlogSearchResponse != null) {
            this.page = new Page(naverBlogSearchResponse.getTotal() == 0, naverBlogSearchResponse.getTotal());
            if (naverBlogSearchResponse.getItems() != null) {
                this.itemList = naverBlogSearchResponse.getItems()
                        .stream()
                        .map(v -> new BlogDto(v))
                        .collect(Collectors.toList());
            }
        }
    }

    @Getter
    public static class Page {

        private boolean isEnd;

        private long totalCount;

        public Page(boolean isEnd, long totalCount) {
            this.isEnd = isEnd;
            this.totalCount = totalCount;
        }
    }

}
