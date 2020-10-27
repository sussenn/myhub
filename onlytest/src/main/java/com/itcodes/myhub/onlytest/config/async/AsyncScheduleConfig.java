package com.itcodes.myhub.onlytest.config.async;

import com.itcodes.myhub.onlytest.thread.async.MyRejectHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * [定时任务异步配置]
 * 最终建议: 异步线程配置 和 定时任务异步配置 都设置. 定时任务使用@Async无需声明Bean/其他异步逻辑使用@Asyn需声明Bean
 * @ClassName AsyncScheduleConfig   异步定时任务 + 异步线程池配置
 * @Author sussen   todo 测试定时任务:定时任务正常异步执行(myAsync-),拒绝策略异步输出(mytask-).----完美执行
 *                  todo 测试@Async: 任务丢失无输出.   只有核心线程池设置生效!最大线程数,队列均失效--任务丢失且无输出
 *
 * @Version 1.0
 * @Data 2019/12/4
 */
//@Configuration
//@EnableAsync
//@EnableScheduling
@Slf4j
public class AsyncScheduleConfig implements SchedulingConfigurer, AsyncConfigurer {

    //自定义拒绝策略
    @Autowired
    private MyRejectHandle rejectHandle;

    /**
     * 异步线程池设置
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//核心线程数
        executor.setMaxPoolSize(16);//最大线程数
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue 生产即被消费的队列
        executor.setQueueCapacity(1000);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间 默认60s
        executor.setThreadNamePrefix("myAsync-");//线程名前缀
        executor.setRejectedExecutionHandler(rejectHandle);//自定义任务丢失处理策略
        //设置线程池等待所有任务都完成再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池等待超时,否则强制关闭
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务异常处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (e, method, objects) -> {
            log.error("异步任务异常:",e);
            log.error("异常所在方法[{}]",method);
        };
    }

    /**
     * 异步任务设置
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = taskScheduler();
        taskRegistrar.setScheduler(taskScheduler);
    }


    /**
     * 拒绝策略输出的线程
     * singleton : bean在每个Spring ioc 容器中只有一个实例。
     * prototype：一个bean的定义可以有多个实例。
     * request：每次http请求都会创建一个bean，该作用域仅在基于web的Spring ApplicationContext情形下有效。
     * session：在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
     * global-session：在一个全局的HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
     * @return
     */
    @Bean
    //@Scope(value = "singleton")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("mytask-");
        scheduler.setAwaitTerminationSeconds(20);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
