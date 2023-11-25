package com.kakaobank.blogsearch.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author : km.oh
 * @since : 2023/11/25
 **/
public class DateUtils {

    public static LocalDate convertStringToLocalDate(String strDate, String format) {
        if (strDate == null || format == null) {
            return null;
        }
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(format));
    }

}
