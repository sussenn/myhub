package com.itcodes.myhub.syslog.controller;

import com.itcodes.myhub.syslog.dao.SysLogDao;
import com.itcodes.myhub.syslog.pojo.base.R;
import com.itcodes.myhub.syslog.pojo.base.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName TestController
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SysLogDao sysLogDao;

    @GetMapping("/get/{id}")
    public R get(@PathVariable Integer id) {
        return new R<>(true, StatusCode.OK,"success", sysLogDao.findById(id));
    }
}
