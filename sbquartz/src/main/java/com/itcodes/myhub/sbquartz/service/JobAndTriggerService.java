package com.itcodes.myhub.sbquartz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcodes.myhub.sbquartz.dao.JobAndTriggerDao;
import com.itcodes.myhub.sbquartz.job.BaseJob;
import com.itcodes.myhub.sbquartz.pojo.entity.JobAndTrigger;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName JobAndTriggerService
 * @Author sussen
 * @Version 1.0
 * @Data 2019/9/4
 */
@Service
public class JobAndTriggerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobAndTriggerDao jobAndTriggerDao;

    //注入的SchedulerConfig有多个Bean,使用@Qualifier指定bean
    @Autowired
    @Qualifier(value = "Scheduler")
    private Scheduler scheduler;

    /*查询所有任务*/
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSiz) {
        PageHelper.startPage(pageNum, pageSiz);
        List<JobAndTrigger> jobAndTrigger = jobAndTriggerDao.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<>(jobAndTrigger);
        return page;
    }

    /*新建任务*/
    public void jobAdd(String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        //启动调度器
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger触发
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败：" + e.getMessage());
            throw new Exception("创建定时任务失败");
        }
    }

    private static BaseJob getClass(String jobClassName) throws Exception {
        Class<?> aClass = Class.forName(jobClassName);
        return (BaseJob) aClass.newInstance();
    }

    /*暂停任务*/
    public void jobPause(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
    }

    /*恢复任务*/
    public void jobResume(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
    }

    /*重启任务*/
    public void jobReschedule(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }
    }

    /*删除任务*/
    public void jobDelete(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }

    }
}
