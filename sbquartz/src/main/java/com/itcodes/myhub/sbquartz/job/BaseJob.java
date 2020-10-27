package com.itcodes.myhub.sbquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @ClassName BaseJob
 * @Author sussen
 * @Version 1.0
 * @Data 2019/9/4
 */
public interface BaseJob extends Job {
    void execute(JobExecutionContext context);
}
