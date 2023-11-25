package com.kakaobank.blogsearch.keyword.controller;

import com.kakaobank.blogsearch.common.exception.vo.CommonResponse;
import com.kakaobank.blogsearch.history.service.KeywordHistorySummaryBatchJob;
import com.kakaobank.blogsearch.keyword.service.KeywordRankService;
import com.kakaobank.blogsearch.keyword.vo.KeywordRankResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@RestController
@RequestMapping("/keyword/v1/rank")
@Api(tags = "인기 검색어 제공 APIs")
@RequiredArgsConstructor
public class KeywordRankController {

    @Value("${constant.topSearchKeywordLimit}")
    private int topSearchKeywordLimit;

    private final KeywordRankService keywordRankService;

    private final KeywordHistorySummaryBatchJob keywordHistorySummaryBatchJob;

    @GetMapping("/top-search")
    @ApiOperation(value = "인기 검색어 제공 API")
    public CommonResponse<KeywordRankResponse> topSearchKeyword() {
        return CommonResponse.success(
                new KeywordRankResponse(keywordRankService.getTopSearchKeywordList(LocalDate.now(), PageRequest.of(0, topSearchKeywordLimit))
                )
        );
    }

    @ApiOperation(value = "키워드 랭킹 배치 실행 ( 테스트용 )")
    @GetMapping("/batch/start")
    public CommonResponse<Void> keywordRankBatchStart() {
        keywordHistorySummaryBatchJob.keywordHistorySummaryBachJob();
        return CommonResponse.success();
    }

}
