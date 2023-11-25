package com.kakaobank.blogsearch.data.repository;

import com.kakaobank.blogsearch.data.entity.KeywordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
public interface KeywordHistoryRepository extends JpaRepository<KeywordHistory, Long> {

    List<KeywordHistory> findAllByCreateDateTimeBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);

}
