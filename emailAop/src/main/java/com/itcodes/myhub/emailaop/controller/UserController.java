package com.itcodes.myhub.emailaop.controller;

import com.itcodes.myhub.emailaop.pojo.R;
import com.itcodes.myhub.emailaop.pojo.StatusCode;
import com.itcodes.myhub.emailaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public R findById(@PathVariable String id) {
        return new R<>(true, StatusCode.OK, "查询成功", userService.findById(id));
    }
}
