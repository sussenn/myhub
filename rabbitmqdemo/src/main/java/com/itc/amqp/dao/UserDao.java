package com.itc.amqp.dao;

import com.itc.amqp.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@Repository
public interface UserDao {

    User findById(String id);
}
