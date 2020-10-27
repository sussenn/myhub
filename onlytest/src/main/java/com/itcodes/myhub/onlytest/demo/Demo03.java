package com.itcodes.myhub.onlytest.demo;

import java.util.concurrent.*;

/**
 * @ClassName Demo03
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/3
 *
 */
public class Demo03 {
    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                //核心线程数
                2,
                //最大线程数
                5,
                //多余线程存活时间
                2L,
                TimeUnit.SECONDS,
                //任务队列(阻塞队列 长度为3)
                new LinkedBlockingDeque<>(3),
                //线程池工厂(默认)
                Executors.defaultThreadFactory(),
                //拒绝策略
                new ThreadPoolExecutor.AbortPolicy());
        //使用线程池
        try {
            executor.execute(() -> {
                //业务逻辑处理...
            });
            }catch (Exception e){
                e.printStackTrace();
            }finally {
            //关闭线程
            executor.shutdown();
            }

    }
}
