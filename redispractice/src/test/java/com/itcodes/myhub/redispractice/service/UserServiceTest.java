package com.itcodes.myhub.redispractice.service;

import com.itcodes.myhub.redispractice.RedisPracticeApplication;
import com.itcodes.myhub.redispractice.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;

@SpringBootTest(classes = RedisPracticeApplication.class)
@RunWith(value = SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Test
    public void test00(){
        Users user = userService.getUserBySid("1000");
        System.out.println(user);
    }

    //获取当前时区时间
    @Test
    public void test10() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);    //2020-04-27T15:55:14.871+08:00[Asia/Shanghai]
    }

    @Test
    public void test01(){
        Users user = userService.getUserByPhone("18888888888");
        String key = "redis:simple:" + user.getId();
        //time单位为 秒,不设置为 -1即永久
        redisService.set(key,user,60);
        Long time = redisService.getExpire(key);
        System.err.println(time);

        System.err.println(redisService.get(key));

    }

    @Test
    public void test02(){
        List<Users> users = userService.getUserByName("萌萌菌");
        String key = "redis:list:all:helloWorld";
        redisService.lPush(key,users);
        System.out.println(redisService.get(key));//无法找到key
        System.err.println(users);
    }

    @Test
    public void test03() {
        List<Users> user = userService.getUserByName("周大拿");
        System.err.println(user);
        Users phone = userService.getUserByPhone("18888888888");
        System.err.println(phone);
    }
}