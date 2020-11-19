package com.itcodes.myhub.syslog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName SuRejectHandle    自定义拒绝策略
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/31
 */
@Component
public class AsyRejectHandle implements RejectedExecutionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AsyRejectHandle.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //打印丢失的任务
        logger.error("线程[{}],[{}]任务丢失", Thread.currentThread().getName(), r);
    }
}
