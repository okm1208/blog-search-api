package com.kakaobank.blogsearch.external.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : km.oh
 * @since : 2023/11/24
 *
 * json convert 테스트
 **/
public class DateTimeParsingTest {

    @Test
    public void dateTimeJsonParsingTest() throws Exception {
        String json = "{ \"createDateTime\" : \"2023-11-15T09:00:27.000+09:00\" }";

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule()).build();
        DateObject dateObject = objectMapper.readValue(json, DateObject.class);

        assertNotNull(dateObject);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        assertThat(dateObject.createDateTime, is(LocalDateTime.parse("2023-11-15 09:00:27", formatter)));
    }

    public static class DateObject {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        LocalDateTime createDateTime;

        public void setCreateDateTime(final LocalDateTime createDateTime) {
            this.createDateTime = createDateTime;
        }

    }

}
