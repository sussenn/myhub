package com.itcodes.myhub.onlytest.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Demo01
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/2
 */
@Slf4j
class MyResource {
    private volatile boolean FLAG = true;
    //原子类
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        log.warn("传入队列:[{}]", blockingQueue.getClass().getName());
    }

    //生产者
    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            //i++
            data = atomicInteger.incrementAndGet() + "";
            //队列添加元素 超时2秒返回false
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                log.info("线程[{}]插入队列[{}]成功", Thread.currentThread().getName(), data);
            } else {
                log.info("线程[{}]插入队列[{}]失败", Thread.currentThread().getName(), data);
            }
            //一秒生产一位元素
            TimeUnit.SECONDS.sleep(1);
        }
        log.error("[{}]线程收到通知,停止生产...", Thread.currentThread().getName());
    }

    //消费者
    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG) {
            //取出元素,超时2秒返回null
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                log.error("[{}]线程消费超时,消费退出...",Thread.currentThread().getName());
                //需要return 否则,会多消费null一次
                return;
            }
            log.info("[{}]线程消费队列[{}]成功", Thread.currentThread().getName(), result);
        }
    }

    //停止操作
    public void stop() {
        this.FLAG = false;
    }
}

@Slf4j
public class Demo01 {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(5));
        //生产线程
        new Thread(() -> {
            log.error("[{}]线程启动...", Thread.currentThread().getName());
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                log.error("线程异常[{}]", e);
            }
        }, "生产者").start();
        //消费线程
        new Thread(() -> {
            log.error("[{}]线程启动...", Thread.currentThread().getName());
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                log.error("线程异常[{}]", e);
            }
        }, "消费者").start();
        //停止操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("5秒时间到,停止操作---------");
        myResource.stop();
    }
}
