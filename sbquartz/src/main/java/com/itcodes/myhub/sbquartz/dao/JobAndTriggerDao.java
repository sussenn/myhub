package com.itcodes.myhub.sbquartz.dao;

import com.itcodes.myhub.sbquartz.pojo.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface JobAndTriggerDao {
    List<JobAndTrigger> getJobAndTriggerDetails();
}
