package com.itcodes.myhub.onlytest.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @ClassName MyJob00
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
//@Component
@Slf4j
public class MyJob01 {

    @Async
    @Scheduled(fixedRate = 2000)
    public void myJob1() {
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务1启动..." + Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 2000)
    public void myJob2() {
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务2启动..." + Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 2000)
    public void myJob3() {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务3启动..." + Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void myJob4() {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务4启动..." + Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void myJob5() {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务5启动..." + Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void myJob6() {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务6启动..." + Thread.currentThread().getName());
    }

    //@Async("myAsyn")
    //@Scheduled(fixedRate = 5000)
    //public void myJob7() {
    //    System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "定时任务7启动..." + Thread.currentThread().getName());
    //}
}
