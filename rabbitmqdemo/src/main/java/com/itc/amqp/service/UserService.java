package com.itc.amqp.service;

import com.itc.amqp.dao.UserDao;
import com.itc.amqp.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className UserService
 * @author sussenn
 * @version 1.0.0
 * @date 2020/11/19
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private RabbitTemplate rabbitTemplate;

    public User findById(String id) {

        rabbitTemplate.convertAndSend("itc.demoExchange", "itc.demoMes", id);

        return userDao.findById(id);
    }


}
