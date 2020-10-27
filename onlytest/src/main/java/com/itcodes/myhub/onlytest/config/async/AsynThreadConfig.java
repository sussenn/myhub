package com.itcodes.myhub.onlytest.config.async;

import com.itcodes.myhub.onlytest.thread.async.MyRejectHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsynThreadConfig  [异步线程的配置]     -------使用@Async需声明所使用的Bean
 * @Author sussen       todo 测试@Async: 线程池设置正常生效 拒绝策略的是主线程输出
 * @Version 1.0         todo 测试定时任务: 在@Async()属性声明bean 定时任务异步生效,但拒绝策略的是scheduling-1线程输出
 * @Data 2019/12/3
 */
//@Configuration
//@EnableAsync
public class AsynThreadConfig {

    @Autowired
    private MyRejectHandle rejectHandle;

    @Bean("myAsyn1")
    public Executor myAsyncc() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//核心线程数
        executor.setMaxPoolSize(10);//最大线程数
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(20);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间 默认60s
        executor.setThreadNamePrefix("myExe1-");//线程名前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//任务丢失处理策略
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    @Bean("myAsync2")
    public Executor myAsync2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//核心线程数
        executor.setMaxPoolSize(15);//最大线程数
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(10);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间
        executor.setThreadNamePrefix("myExe2-");//线程名前缀
        executor.setRejectedExecutionHandler(rejectHandle);// 自定义任务丢失处理策略
        //设置线程池等待所有任务都完成再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

}
