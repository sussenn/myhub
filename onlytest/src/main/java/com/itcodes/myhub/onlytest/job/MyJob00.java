package com.itcodes.myhub.onlytest.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyJob00
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
//@Component
@Slf4j
public class MyJob00 {

    @Async("myAsyn2")
    @Scheduled(fixedRate = 2000)
    public void myJobA(){
        log.info("定时任务A启动..." + Thread.currentThread().getName());
    }

    @Async("myAsyn2")
    @Scheduled(fixedDelay = 2000)
    public void myJobB(){
        log.info("定时任务B启动..." + Thread.currentThread().getName());
    }

    @Async("myAsyn2")
    @Scheduled(fixedDelay = 2000)
    public void myJobC(){
        log.info("定时任务C启动..." + Thread.currentThread().getName());
    }

    @Async("myAsyn2")
    @Scheduled(fixedDelay = 2000)
    public void myJobD(){
        log.info("定时任务D启动..." + Thread.currentThread().getName());
    }

    @Async("myAsyn2")
    @Scheduled(fixedDelay = 2000)
    public void myJobE(){
        log.info("定时任务E启动..." + Thread.currentThread().getName());
    }

    @Async("myAsyn2")
    @Scheduled(fixedDelay = 2000)
    public void myJobF(){
        log.info("定时任务F启动..." + Thread.currentThread().getName());
    }

    @Async("myAsyn2")
    @Scheduled(fixedDelay = 2000)
    public void myJobG(){
        log.info("定时任务G启动..." + Thread.currentThread().getName());
    }
}
