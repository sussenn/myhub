package com.itcodes.myhub.redistemplatepractice;

import com.itcodes.myhub.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName RedisTemplatePracticeApplication  RedisTemplate实践
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/10/27
 */
@SpringBootApplication
public class RedisTemplatePracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisTemplatePracticeApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
