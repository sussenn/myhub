package com.itcodes.myhub.redistemplatepractice.dao;

import com.itcodes.myhub.redistemplatepractice.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/10/27
 */
@Component
@Mapper
public interface UserDao {

    User findById(Long id);

    //@Select("select * from redis_user")
    List<User> findAll();
}
