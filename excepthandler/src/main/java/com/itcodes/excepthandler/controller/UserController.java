package com.itcodes.excepthandler.controller;

import com.itcodes.excepthandler.pojo.R;
import com.itcodes.excepthandler.pojo.StatusCode;
import com.itcodes.excepthandler.pojo.User;
import com.itcodes.excepthandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public R findUserById(@PathVariable Long id){

        User user = userService.findById(id);
        return new R(true, StatusCode.OK,"查询成功",user);
    }
}
