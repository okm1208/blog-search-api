package com.kakaobank.blogsearch.search.controller;

import com.kakaobank.blogsearch.common.enums.CommonSort;
import com.kakaobank.blogsearch.common.exception.vo.CommonResponse;
import com.kakaobank.blogsearch.data.dto.PaginationDto;
import com.kakaobank.blogsearch.external.vo.kakao.request.KakaoBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.kakao.response.KakaoBlogSearchResponse;
import com.kakaobank.blogsearch.external.vo.naver.request.NaverBlogSearchRequest;
import com.kakaobank.blogsearch.external.vo.naver.response.NaverBlogSearchResponse;
import com.kakaobank.blogsearch.search.service.SearchApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@RestController
@RequestMapping("/blog/v1")
@Api(tags = "블로그 검색 APIs")
@RequiredArgsConstructor
public class BlogSearchController {

    private final SearchApiService<KakaoBlogSearchRequest, KakaoBlogSearchResponse> kakaoBlogSearchService;

    private final SearchApiService<NaverBlogSearchRequest, NaverBlogSearchResponse> naverBlogSearchService;

    @GetMapping("/kakao/search")
    @ApiOperation(value = "블로그 검색 리스트 API ( kakao )")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", defaultValue = "15"),
    })
    public CommonResponse<PaginationDto> searchKakaoBlog(@RequestParam("query") String query,
                                                         @RequestParam(value = "sort", required = false) CommonSort sort,
                                                         @ApiIgnore @PageableDefault(size = 15) Pageable pageable
    ) {
        KakaoBlogSearchResponse response = kakaoBlogSearchService.search(new KakaoBlogSearchRequest(query,
                com.kakaobank.blogsearch.external.vo.kakao.enums.Sort.convertSort(sort), pageable));
        return CommonResponse.success(new PaginationDto(response));
    }

    @GetMapping("/naver/search")
    @ApiOperation(value = "블로그 검색 리스트 API ( naver )")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", defaultValue = "15"),
    })
    public CommonResponse<PaginationDto> searchNaverBlog(@RequestParam("query") String query,
                                                         @RequestParam(value = "sort", required = false) CommonSort sort,
                                                         @ApiIgnore @PageableDefault(size = 15) Pageable pageable) {
        NaverBlogSearchResponse response = naverBlogSearchService.search(new NaverBlogSearchRequest(query,
                com.kakaobank.blogsearch.external.vo.naver.enums.Sort.convertSort(sort), pageable));
        return CommonResponse.success(new PaginationDto(response));
    }

}
