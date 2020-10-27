package com.itcodes.myhub.sbquartz.controller;

import com.github.pagehelper.PageInfo;
import com.itcodes.myhub.sbquartz.pojo.entity.JobAndTrigger;
import com.itcodes.myhub.sbquartz.service.JobAndTriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JobController
 * @Author sussen
 * @Version 1.0
 * @Data 2019/9/4
 */
@RestController
@RequestMapping("/job")
public class JobController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    /**
     * 新建任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value = "/addjob")
    public void addJob(@RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        jobAndTriggerService.jobAdd(jobClassName, jobGroupName, cronExpression);
        logger.info("定时任务新建成功：" + jobClassName);
    }

    /**
     * 暂停任务
     *
     * @param jobClassName
     * @param jobGroupName
     */
    @PostMapping(value = "/pausejob")
    public void pauseJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) {
        jobAndTriggerService.jobPause(jobClassName, jobGroupName);
        logger.info("定时任务已暂停：" + jobClassName);
    }

    /**
     * 恢复任务
     *
     * @param jobClassName
     * @param jobGroupName
     */
    @PostMapping(value = "/resumejob")
    public void resumeJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) {
        jobAndTriggerService.jobResume(jobClassName, jobGroupName);
        logger.info("定时任务已恢复：" + jobClassName);
    }

    /**
     * 重启任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     */
    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName, @RequestParam(value = "cronExpression") String cronExpression) {
        jobAndTriggerService.jobReschedule(jobClassName, jobGroupName, cronExpression);
        logger.info("定时任务已重启：" + jobClassName);
    }

    /**
     * 删除任务
     *
     * @param jobClassName
     * @param jobGroupName
     */
    @PostMapping(value = "/deletejob")
    public void deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) {
        jobAndTriggerService.jobDelete(jobClassName, jobGroupName);
        logger.info("定时任务删除成功：" + jobClassName);
    }

    /**
     * 查询所有任务
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }
}
