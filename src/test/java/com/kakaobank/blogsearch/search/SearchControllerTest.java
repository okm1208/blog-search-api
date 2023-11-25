package com.kakaobank.blogsearch.search;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : km.oh
 * @since : 2023/11/24
 *
 * 검색 컨트롤러 테스트
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SearchControllerTest {

    private static final String BLOG_BASE_URL = "/blog/v1";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void kakaoSearchSuccessCallTest() throws Exception {
        mockMvc.perform(get(BLOG_BASE_URL + "/kakao/search")
                        .param("page", "1")
                        .param("size", "10")
                        .param("query", "카카오뱅크")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.page").exists())
                .andExpect(jsonPath("$.data.itemList").exists())
                .andExpect(jsonPath("$.data.itemList").isNotEmpty())
                .andDo(print());

    }

    @Test
    public void kakaoSearchCustomErrorTest() throws Exception {
        mockMvc.perform(get(BLOG_BASE_URL + "/kakao/search")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}