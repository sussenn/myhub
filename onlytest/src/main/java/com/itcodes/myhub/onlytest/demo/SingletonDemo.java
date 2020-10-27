package com.itcodes.myhub.onlytest.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName SingletonDemo
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/7
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println("始终只有1个实例");
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        
        //单线程
        //System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        //多线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
