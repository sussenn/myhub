package com.itcodes.myhub.mybatisboot;

import com.itcodes.myhub.util.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName MyBatisBootApplication
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/3
 */
@SpringBootApplication

public class MyBatisBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisBootApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,3);
    }
}
