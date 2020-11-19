package com.itcodes.myhub.syslog.service;

import com.itcodes.myhub.syslog.dao.SysLogDao;
import com.itcodes.myhub.syslog.pojo.SysLogVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName SysLogService
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@Service
public class SysLogService {

    @Resource
    private SysLogDao sysLogDao;

    public void addSysLog(SysLogVo sysLog) {
        sysLogDao.addSysLog(sysLog);
    }
}
