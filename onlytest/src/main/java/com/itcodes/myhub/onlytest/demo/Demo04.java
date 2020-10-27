package com.itcodes.myhub.onlytest.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo04    死锁demo
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/3
 */
@Slf4j
class MyLockDemo implements Runnable{
    private String lockA;
    private String lockB;

    public MyLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            log.info("[{}]线程持有锁[{}],尝试获取锁[{}]",Thread.currentThread().getName(),lockA,lockB);
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

            synchronized (lockB){
                log.info("[{}]线程持有锁[{}],尝试获取锁[{}]",Thread.currentThread().getName(),lockB,lockA);
            }
        }
    }
}

public class Demo04 {
    public static void main(String[] args) {
         String lockA = "lockA";
         String lockB = "lockB";

        new Thread(new MyLockDemo(lockA,lockB),"AAA").start();
        new Thread(new MyLockDemo(lockB,lockA),"BBB").start();
    }
}
