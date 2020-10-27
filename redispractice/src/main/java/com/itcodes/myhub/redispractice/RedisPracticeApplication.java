package com.itcodes.myhub.redispractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName RedisPracticeApplication  Redis实践
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/19
 */
@EnableCaching
@SpringBootApplication
public class RedisPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisPracticeApplication.class,args);
    }
}
