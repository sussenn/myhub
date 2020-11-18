package com.itcodes.myhub.eventlisten.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName DomainEventAsyncConfig
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
@Configuration
@EnableAsync
public class DomainEventAsyncConfig {

    public static final String DOMAIN_EXECUTOR = "domainExecutor";

    @Bean(DOMAIN_EXECUTOR)
    public Executor DomainEventExe() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);//核心线程数
        executor.setMaxPoolSize(16);//最大线程数     cpu核数/(1-0.8)//cup核数*2//cup核数+1
        //传参正值使用无界LinkedBlockingQueue
        //其他传参使用不缓存SynchronousQueue
        executor.setQueueCapacity(20);//队列长度(超过队列长度无法存储,则开启最大线程数)
        executor.setKeepAliveSeconds(30);//空闲线程最大存活时间 默认60s
        executor.setThreadNamePrefix("domainEvent-");//线程名前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//任务丢失处理策略
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
