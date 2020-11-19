package com.itcodes.myhub.syslog.dao;

import com.itcodes.myhub.syslog.pojo.SysLogVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName SysLogDao
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
//@Repository
@Mapper
public interface SysLogDao {

    void addSysLog(SysLogVo sysLog);

    SysLogVo findById(Integer id);

}
