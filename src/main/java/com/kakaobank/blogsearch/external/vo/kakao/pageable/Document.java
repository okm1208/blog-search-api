package com.kakaobank.blogsearch.external.vo.kakao.pageable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakaobank.blogsearch.common.utils.DateUtils;
import com.kakaobank.blogsearch.external.vo.naver.pageable.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author : km.oh
 * @since : 2023/11/23
 **/
@NoArgsConstructor
@Getter
public class Document {

    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime datetime;

    public Document(Item item) {
        this.title = item.getTitle();
        this.contents = item.getDescription();
        this.url = item.getLink();
        this.blogname = item.getBloggername();
        LocalDate postDate = DateUtils.convertStringToLocalDate(item.getPostdate(), "yyyyMMdd");
        if (postDate != null) {
            this.datetime =
                    postDate.atStartOfDay(ZoneId.systemDefault());
        }
    }

}
