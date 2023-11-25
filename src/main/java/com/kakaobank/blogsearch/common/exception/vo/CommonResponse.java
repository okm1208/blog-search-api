package com.kakaobank.blogsearch.common.exception.vo;

import lombok.*;

/**
 * @author : km.oh
 * @since : 2023/11/24
 **/
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T>{
    private String message;
    private T data;
    private int errorCode;

    public static <T> CommonResponse<T> success(T t) {
        return CommonResponse.<T>builder()
                .data(t)
                .build();
    }
    public static <T> CommonResponse<T> success() {
        return CommonResponse.<T>builder()
                .build();
    }

    public static <Void> CommonResponse<Void> fail(final int errorCode, final String errorMessage){
        return CommonResponse.<Void>builder()
                .message(errorMessage)
                .errorCode(errorCode)
                .build();
    }

}
