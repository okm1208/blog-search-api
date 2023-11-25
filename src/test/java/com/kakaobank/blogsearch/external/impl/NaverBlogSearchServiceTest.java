package com.kakaobank.blogsearch.external.impl;

import com.kakaobank.blogsearch.common.config.ExternalErrorMessageProperties;
import com.kakaobank.blogsearch.common.exception.BadRequestException;
import com.kakaobank.blogsearch.data.entity.KeywordHistory;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryRepository;
import com.kakaobank.blogsearch.external.vo.naver.enums.Sort;
import com.kakaobank.blogsearch.external.vo.naver.pageable.Item;
import com.kakaobank.blogsearch.external.vo.naver.request.NaverBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.naver.response.NaverBlogSearchResponse;
import com.kakaobank.blogsearch.search.service.SearchApiService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : km.oh
 * @since : 2023/11/25
 * <p>
 * 네이버 블로그 검색 , 예외 처리, 키워드 저장 테스트
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "spring.profiles.active=local")
@Transactional
class NaverBlogSearchServiceTest {

    @Autowired
    @Qualifier("naverBlogSearchService")
    private SearchApiService<NaverBlogSearchRequest, NaverBlogSearchResponse> naverBlogSearchService;

    @Autowired
    private KeywordHistoryRepository keywordHistoryRepository;

    @Test
    @Order(3)
    public void errorHandleTest() {
        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                () -> naverBlogSearchService.search(
                        NaverBlogSearchRequest.builder()
                                .query("네이버웹툰")
                                .sort(Sort.SIM)
                                .start(0)
                                .display(10)
                                .build()
                )
        );
        assertThat(thrown.getMessage(), is(ExternalErrorMessageProperties.NAVER_API_BAD_REQUEST));
    }

    @Test
    @Order(2)
    public void paginationTest() {
        NaverBlogSearchResponse naverBlogSearchResponse =
                naverBlogSearchService.search(
                        NaverBlogSearchRequest.builder()
                                .query("네이버웹툰")
                                .sort(Sort.SIM)
                                .start(1)
                                .display(10)
                                .build()
                );
        assertThat(naverBlogSearchResponse, is(notNullValue()));
        assertThat(naverBlogSearchResponse.getTotal(), greaterThan(0L));

        List<Item> itemList = naverBlogSearchResponse.getItems();
        assertThat(itemList, is(notNullValue()));
        assertThat(itemList.size(), is(10));

    }

    @Test
    @Order(1)
    public void keywordHistorySaveTest() {
        naverBlogSearchService.search(NaverBlogSearchRequest.builder()
                .query("미국주식")
                .sort(Sort.SIM)
                .start(1)
                .display(10)
                .build());
        naverBlogSearchService.search(NaverBlogSearchRequest.builder()
                .query("미국주식")
                .sort(Sort.SIM)
                .start(1)
                .display(10)
                .build());
        naverBlogSearchService.search(NaverBlogSearchRequest.builder()
                .query("금리인상")
                .sort(Sort.SIM)
                .start(1)
                .display(10)
                .build());
        naverBlogSearchService.search(NaverBlogSearchRequest.builder()
                .query("구글")
                .sort(Sort.SIM)
                .start(1)
                .display(10)
                .build());

        List<KeywordHistory> keywordHistoryList = keywordHistoryRepository.findAll();

        long keywordCount1 = keywordHistoryList.stream().filter(v -> v.getKeyword().equals("금리인상")).count();
        assertThat(keywordCount1, is(1L));
        long keywordCount2 = keywordHistoryList.stream().filter(v -> v.getKeyword().equals("구글")).count();
        assertThat(keywordCount2, is(1L));
        long keywordCount3 = keywordHistoryList.stream().filter(v -> v.getKeyword().equals("미국주식")).count();
        assertThat(keywordCount3, is(2L));

    }

}