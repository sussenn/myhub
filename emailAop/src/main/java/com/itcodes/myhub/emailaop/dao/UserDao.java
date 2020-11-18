package com.itcodes.myhub.emailaop.dao;

import com.itcodes.myhub.emailaop.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserDao
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/17
 */
@Component
@Mapper
public interface UserDao {
    User findById(String id);
}
