package com.kakaobank.blogsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy
@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class KakaoBlogSearchAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoBlogSearchAssignmentApplication.class, args);
    }

}
