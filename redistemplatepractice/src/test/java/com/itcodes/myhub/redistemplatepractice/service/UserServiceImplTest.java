package com.itcodes.myhub.redistemplatepractice.service;

import com.itcodes.myhub.redistemplatepractice.RedisTemplatePracticeApplication;
import com.itcodes.myhub.redistemplatepractice.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = RedisTemplatePracticeApplication.class)
@RunWith(value = SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test02() {
        User user = userService.findById(10000L);
        Long id = user.getId();
        //hash 存入redis
        redisTemplate.boundHashOps("redisTem:user:me:").put(id, user);
        //根据key获取
        User userGet = (User) redisTemplate.boundHashOps("redisTem:user:me:").get(id);
        System.err.println(userGet);
        //根据key删除
        redisTemplate.boundHashOps("redisTem:user:me:").delete(id);
        //
    }

    @Test
    public void test00() {
        User user = userService.findById(10000L);
        System.err.println(user);
    }

    @Test
    public void test01() {
        List<User> all = userService.findAll();
        System.err.println(all);
    }
}