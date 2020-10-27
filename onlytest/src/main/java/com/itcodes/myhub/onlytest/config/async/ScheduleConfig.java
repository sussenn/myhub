package com.itcodes.myhub.onlytest.config.async;

import com.itcodes.myhub.onlytest.thread.async.MyRejectHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @ClassName ScheduleConfig    定时任务多线程的配置
 * @Author sussen   TODO 这个实现类无法覆盖spring定时任务内置线程池   使用自定义配置异步Async线程池可以覆盖
 * @Version 1.0
 * @Data 2019/12/3
 */
//@Configuration
//@EnableScheduling
public class ScheduleConfig extends SimpleAsyncTaskExecutor implements SchedulingConfigurer {

    @Autowired
    private MyRejectHandle rejectHandle;

    //控制并发线程的上限
    /*@Override
    public void setConcurrencyLimit(int concurrencyLimit) {
        concurrencyLimit = 5;
        super.setConcurrencyLimit(concurrencyLimit);
    }

    //线程名前缀
    @Override
    public String getThreadNamePrefix() {

        return super.getThreadNamePrefix();
    }

    //线程守护
    @Override
    public boolean isDaemon() {
        return super.isDaemon();
    }*/


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
//------------------------------------------------------------------------------
        /*ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("MyJob-pool-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(5, 20,
                5L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, rejectHandle);

        pool.execute(() -> System.err.println(Thread.currentThread().getName()));
        pool.shutdown();*/

//------------------------------------------------------------------------------
        /*ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5,
                new BasicThreadFactory
                        .Builder()
                        .namingPattern("MyJob-one-%d")
                        .daemon(true)
                        .build());

        executor.scheduleAtFixedRate(() -> {
            //System.err.println("线程开始"+ Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.err.println("线程执行完毕");
        }, 1, 2, TimeUnit.SECONDS);
        //注入定时任务容器
        taskRegistrar.setScheduler(executor);*/
    }
}

