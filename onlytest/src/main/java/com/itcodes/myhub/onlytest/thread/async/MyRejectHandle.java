package com.itcodes.myhub.onlytest.thread.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName MyRejectHandle    自定义线程池任务拒绝策略
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
@Component
@Slf4j
public class MyRejectHandle implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //打印丢失的任务
        log.error("线程[{}],[{}]任务丢失",Thread.currentThread().getName(),r);
    }
}
