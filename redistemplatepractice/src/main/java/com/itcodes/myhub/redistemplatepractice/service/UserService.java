package com.itcodes.myhub.redistemplatepractice.service;

import com.itcodes.myhub.redistemplatepractice.pojo.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/10/27
 */
public interface UserService {

    User findById(Long id);

    List<User> findAll();
}
