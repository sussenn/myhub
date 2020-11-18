package com.itcodes.myhub.emailaop.service;

import com.itcodes.myhub.emailaop.EmailAopApplication;
import com.itcodes.myhub.emailaop.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = EmailAopApplication.class)
@RunWith(value = SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test00() {
        //User byId = userService.findById("1");
        User byId = userService.findById("2");
        System.err.println(byId);
    }

}