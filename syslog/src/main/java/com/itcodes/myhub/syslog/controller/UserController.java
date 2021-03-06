package com.itcodes.myhub.syslog.controller;

import com.itcodes.myhub.syslog.annotation.SysLog;
import com.itcodes.myhub.syslog.pojo.User;
import com.itcodes.myhub.syslog.pojo.base.R;
import com.itcodes.myhub.syslog.pojo.base.StatusCode;
import com.itcodes.myhub.syslog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserController
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@RestController
@RequestMapping(path = "/user", produces = "application/json")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findById/{id}")
    public R<User> findById(@PathVariable String id) {
        return new R<>(true, StatusCode.OK, "查询成功", userService.findById(id));
    }

    @SysLog("查询所有的接口")
    @GetMapping("/findAll")
    public R<List<User>> findAll() {
        return new R<>(true, StatusCode.OK, "查询成功", userService.findAll());
    }
}
