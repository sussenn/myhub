package com.itcodes.myhub.sbquartz.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @ClassName NewJob
 * @Author sussen
 * @Version 1.0
 * @Data 2019/9/4
 */
public class NewJob implements BaseJob {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public NewJob() {

    }

    @Override
    public void execute(JobExecutionContext context) {
        logger.info("New Job执行时间: " + new Date());
    }
}
