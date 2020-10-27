package com.itcodes.myhub.onlytest.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName myAsynTest00Service
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
@Service
public class MyAsynTest00Service {

    @Async("myAsyn")
    public void asyn03(){
        System.err.println("任务asyn03()线程名:--->" + Thread.currentThread().getName());
        AtomicInteger integer = new AtomicInteger(0);
        //int count = 0;
        //CountDownLatch count = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            int count = integer.incrementAndGet();
            //count ++;
            System.err.println(count);
        }
    }

    @Async("myAsyn")
    public void asyn00(){
        System.err.println("无参无返回异步...");
        System.err.println("任务asyn00()线程名:--->" + Thread.currentThread().getName());
    }

    @Async("myAsyn")
    public void asyn01(String s){
        System.err.println("有参无返回异步:" + s);
        System.err.println("任务asyn01()线程名:--->" + Thread.currentThread().getName());
    }

    @Async("myAsyn")
    public Future<String> asyn02(int i){
        System.err.println("有参返回Future异步:" + i);
        System.err.println("任务asyn02()线程名:--->" + Thread.currentThread().getName());
        Future<String> future;

        try {
            Thread.sleep(1000 * 1);
            future = new AsyncResult<>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<>("error");
        }
        return future;
    }
}
