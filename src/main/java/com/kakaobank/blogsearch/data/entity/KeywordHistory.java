package com.kakaobank.blogsearch.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String keyword;

    @Column(nullable = false)
    private LocalDateTime createDateTime;

    public KeywordHistory(final String keyword) {
        this.keyword = keyword;
        this.createDateTime = LocalDateTime.now();
    }


}
