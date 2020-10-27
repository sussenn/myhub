package com.itcodes.myhub.onlytest.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Demo02    第3种 获得多线程的方法
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/2
 */
//带返回值的多线程接口
class myThread implements Callable<Integer> {
    @Override
    public Integer call() {
        return 1024;
    }
}


public class Demo02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask接口属于Runnable子接口. 构造方法需要传参Callable接口[适配模式]
         FutureTask<Integer> futureTask = new FutureTask<>(new myThread());
         //thread接口 构造方法需要传参Runnable接口
        //以上操作将Callable接口和Thread关联上
        new Thread(futureTask, "AAA").start();
        //探知callable线程是否执行完毕
        //boolean succ = futureTask.isDone();
        //获取异步线程Callable的返回结果   建议放在最后
        Integer number = futureTask.get();
    }
}
