package com.kakaobank.blogsearch.external.vo.naver.response;

import com.kakaobank.blogsearch.external.vo.naver.pageable.Item;
import lombok.Getter;

import java.util.List;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
@Getter
public class NaverBlogSearchResponse {

    private long total;

    private int start;

    private int display;

    private List<Item> items;

    public boolean isEnd() {
        if (items != null) {
            return items.isEmpty();
        }
        return true;
    }

}
