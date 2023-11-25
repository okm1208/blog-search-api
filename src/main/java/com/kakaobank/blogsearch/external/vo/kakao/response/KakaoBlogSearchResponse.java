package com.kakaobank.blogsearch.external.vo.kakao.response;

import com.kakaobank.blogsearch.external.vo.kakao.pageable.Document;
import com.kakaobank.blogsearch.external.vo.kakao.pageable.Meta;
import com.kakaobank.blogsearch.external.vo.naver.pageable.Item;
import com.kakaobank.blogsearch.external.vo.naver.request.NaverBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.naver.response.NaverBlogSearchResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@Getter
public class KakaoBlogSearchResponse {

    private Meta meta;

    private List<Document> documents;

    public static KakaoBlogSearchResponse of(NaverBlogSearchResponse naverBlogSearchResponse) {

        KakaoBlogSearchResponse kakaoBlogSearchResponse = new KakaoBlogSearchResponse();
        kakaoBlogSearchResponse.meta = new Meta(naverBlogSearchResponse.isEnd(),
                naverBlogSearchResponse.getItems().size(),
                naverBlogSearchResponse.getTotal());
        kakaoBlogSearchResponse.documents = naverBlogSearchResponse.getItems()
                .stream().map(item -> new Document(item))
                .collect(Collectors.toList());
        return kakaoBlogSearchResponse;
    }

}
