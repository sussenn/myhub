package com.itcodes.myhub.onlytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @ClassName OnlytestApplication
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/27
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class OnlytestApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlytestApplication.class,args);
    }


    /*@Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setDaemon(true);
        scheduler.setThreadNamePrefix("myJob-");
        scheduler.setPoolSize(5);
        return scheduler;
    }*/
}
