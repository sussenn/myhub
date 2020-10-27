package com.itcodes.myhub.onlytest.config.async;

import com.itcodes.myhub.onlytest.thread.async.MyRejectHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @ClassName ScheduleConfig2
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/4
 */
//@Configuration
public class ScheduleConfig2 implements SchedulingConfigurer {

    @Autowired
    private MyRejectHandle rejectHandle;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("myJob-");
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(1);
        executor.setRejectedExecutionHandler(rejectHandle);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(20);
        executor.initialize();
        taskRegistrar.setScheduler(executor);
    }
}
