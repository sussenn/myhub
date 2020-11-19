package com.itcodes.myhub.syslog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName EventAsyncConfig
 *                          拒绝策略:
 *                                  1. new ThreadPoolExecutor.AbortPolicy(默认): 直接抛出异常, 中断程序
 *                                  2.      CallerRunsPolicy: 将多余任务回退给调用者线程(main),让调用者线程执行
 *                                  3.      DiscardOldestPolicy: 丢弃队列最后的任务,将当前任务添加到队列
 *                                  4.      DiscardPolicy: 直接丢弃多余的任务,不作任何处理
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
@Configuration
@EnableAsync
public class EventAsyncConfig {

    @Resource
    private AsyRejectHandle rejectHandle;

    public static final String EVENT_EXECUTOR = "eventExecutor";

    @Bean(EVENT_EXECUTOR)
    public Executor DomainEventExe() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);                            //核心线程数
        executor.setMaxPoolSize(16);                            //最大线程数     cpu核数/(1-0.8)//cup核数*2//cup核数+1
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(1024);                        //队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);                       //空闲线程最大存活时间 默认60s
        executor.setThreadNamePrefix("eventExecutor-");           //线程名前缀
        executor.setRejectedExecutionHandler(rejectHandle);     //任务丢失处理策略
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
