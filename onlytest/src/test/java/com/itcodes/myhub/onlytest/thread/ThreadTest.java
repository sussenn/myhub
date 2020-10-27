package com.itcodes.myhub.onlytest.thread;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadTest
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/27
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class ThreadTest {

    /**
     * 缓存线程池
     */
    @Test
    public void cachedThread() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (Exception ignored) {

                    }
                    System.err.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            });
        }
    }

    /**
     * 固定长度线程池  [有输出]
     */
    @Test
    public void fixedThread() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            });
        }
    }

    /**
     * 固定长度周期性线程池   延迟3s执行  schedule方法
     */
    @Test
    public void scheduledThread() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.err.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

    /**
     * 单线程池     [有输出]
     */
    @Test
    public void singleThread() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.println("index:" + index);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            });
        }
    }

}
