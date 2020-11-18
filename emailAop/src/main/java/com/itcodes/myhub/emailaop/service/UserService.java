package com.itcodes.myhub.emailaop.service;

import com.itcodes.myhub.emailaop.dao.UserDao;
import com.itcodes.myhub.emailaop.exception.MyException;
import com.itcodes.myhub.emailaop.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/17
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User findById(String id) {
        if (id.equals("2")) {
            throw new MyException("查无数据");
        }
        return userDao.findById(id);
    }
}
