package com.kakaobank.blogsearch.external.impl;

import com.kakaobank.blogsearch.common.config.ExternalErrorMessageProperties;
import com.kakaobank.blogsearch.common.exception.BadRequestException;
import com.kakaobank.blogsearch.data.entity.KeywordHistory;
import com.kakaobank.blogsearch.data.repository.KeywordHistoryRepository;
import com.kakaobank.blogsearch.external.vo.kakao.enums.Sort;
import com.kakaobank.blogsearch.external.vo.kakao.pageable.Document;
import com.kakaobank.blogsearch.external.vo.kakao.pageable.Meta;
import com.kakaobank.blogsearch.external.vo.kakao.request.KakaoBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
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
 * @since : 2023/11/23
 * <p>
 * 카카오 블로그 검색 , 예외 처리, 키워드 저장 테스트
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "spring.profiles.active=local")
@Transactional
class KakaoBlogSearchServiceTest {

    @Autowired
    @Qualifier("kakaoBlogSearchService")
    private SearchApiService<KakaoBlogSearchRequest, KakaoBlogSearchResponse> kakaoBlogSearchService;

    @Autowired
    private KeywordHistoryRepository keywordHistoryRepository;

    @Test
    @Order(3)
    public void errorHandleTest() {
        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                () -> kakaoBlogSearchService.search(
                        KakaoBlogSearchRequest.builder()
                                .query("라이언 이벤트")
                                .sort(Sort.ACCURACY)
                                .page(0)
                                .size(10)
                                .build()
                )
        );
        assertThat(thrown.getMessage(), is(ExternalErrorMessageProperties.KAKAO_API_BAD_REQUEST));
    }

    @Test
    @Order(2)
    public void paginationTest() {
        KakaoBlogSearchResponse kakaoBlogSearchResponse = kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("크리스마스 이벤트")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());

        assertThat(kakaoBlogSearchResponse, is(notNullValue()));

        Meta meta = kakaoBlogSearchResponse.getMeta();
        assertThat(meta, is(notNullValue()));
        assertThat(meta.isEnd(), is(false));
        assertThat(meta.getTotalCount(), greaterThan(0L));

        List<Document> documentList = kakaoBlogSearchResponse.getDocuments();
        assertThat(documentList, is(notNullValue()));
        assertThat(documentList.size(), is(10));
    }

    @Test
    @Order(1)
    public void keywordHistorySaveTest() {
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("한진해운")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("또또은행")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("너의이름은")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());
        kakaoBlogSearchService.search(KakaoBlogSearchRequest.builder()
                .query("너의이름은")
                .sort(Sort.ACCURACY)
                .page(1)
                .size(10)
                .build());

        List<KeywordHistory> keywordHistoryList = keywordHistoryRepository.findAll();

        long keywordCount1 = keywordHistoryList.stream().filter(v -> v.getKeyword().equals("한진해운")).count();
        assertThat(keywordCount1, is(1L));
        long keywordCount2 = keywordHistoryList.stream().filter(v -> v.getKeyword().equals("또또은행")).count();
        assertThat(keywordCount2, is(1L));
        long keywordCount3 = keywordHistoryList.stream().filter(v -> v.getKeyword().equals("너의이름은")).count();
        assertThat(keywordCount3, is(2L));

    }

}