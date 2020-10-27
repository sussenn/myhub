package com.itcodes.myhub.onlytest.thread;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import com.itcodes.myhub.onlytest.pojo.MyCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName Top03Test
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/4
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class Top03Test {

    @Test
    public void test00(){
        try {
            CyclicBarrier barrier = new CyclicBarrier(3);
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void test01() throws ExecutionException, InterruptedException {
        //FutureTask<String> future = new FutureTask<>(new MyCallable("aaa"));
        //
        //ExecutorService executor = Executors.newFixedThreadPool(2);
        //
        //Future<?> fu = executor.submit(future);
        //String result = future.get();
        //Object obj = fu.get();
        //
        //Semaphore sem = new Semaphore(5);
        //sem.acquire();
        //sem.release();
        //
        //ReentrantLock reenLock = new ReentrantLock();
        //reenLock.lock();
        //reenLock.unlock();
        //
        //Condition condition = reenLock.newCondition();
        //condition.await();
        //condition.signal();

        //ReentrantReadWriteLock reenLock2 = new ReentrantReadWriteLock();
        //ReentrantReadWriteLock.ReadLock readLock = reenLock2.readLock();
        //ReentrantReadWriteLock.WriteLock writeLock = reenLock2.writeLock();
        //readLock.lock();
        //readLock.unlock();

    }
}
