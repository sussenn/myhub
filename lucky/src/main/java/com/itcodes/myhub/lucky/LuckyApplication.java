package com.itcodes.myhub.lucky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName LuckyApplication  幸运女神
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/22
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class LuckyApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuckyApplication.class,args);
    }
}
