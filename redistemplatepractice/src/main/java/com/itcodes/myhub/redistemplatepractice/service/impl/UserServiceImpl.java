package com.itcodes.myhub.redistemplatepractice.service.impl;

import com.itcodes.myhub.redistemplatepractice.dao.UserDao;
import com.itcodes.myhub.redistemplatepractice.pojo.User;
import com.itcodes.myhub.redistemplatepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/10/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
