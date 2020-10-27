package com.itcodes.myhub.onlytest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyAsynTest01Service
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
@Service
@Slf4j
public class MyAsynTest01Service {
    @Async("myAsyn2")
    public void doTaskA() {
        log.info("任务A线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }

        Long endTime = System.currentTimeMillis();
        log.info("任务A线程耗时:--->" + (endTime - startTime));
    }

    @Async("myAsyn2")
    public void doTaskB() {
        log.info("任务B线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }
        Long endTime = System.currentTimeMillis();
        log.info("任务B线程耗时:--->" + (endTime - startTime));
    }

    @Async("myAsyn2")
    public void doTaskC() {
        log.info("任务C线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }
        Long endTime = System.currentTimeMillis();
        log.info("任务C线程耗时:--->" + (endTime - startTime));
    }

    @Async("myAsyn2")
    public void doTaskD() {
        log.info("任务D线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }
        Long endTime = System.currentTimeMillis();
        log.info("任务D线程耗时:--->" + (endTime - startTime));
    }

    @Async("myAsyn2")
    public void doTaskE() {
        log.info("任务E线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }
        Long endTime = System.currentTimeMillis();
        log.info("任务E线程耗时:--->" + (endTime - startTime));
    }

    @Async("myAsyn2")
    public void doTaskF() {
        log.info("任务F线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }
        Long endTime = System.currentTimeMillis();
        log.info("任务F线程耗时:--->" + (endTime - startTime));
    }

    @Async("myAsyn2")
    public void doTaskG() {
        log.info("任务G线程名:--->" + Thread.currentThread().getName());
        Long startTime = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("sleep异常[{}]",e);
        }
        Long endTime = System.currentTimeMillis();
        log.info("任务G线程耗时:--->" + (endTime - startTime));
    }
}
