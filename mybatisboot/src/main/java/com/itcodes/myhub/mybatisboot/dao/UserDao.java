package com.itcodes.myhub.mybatisboot.dao;

import com.itcodes.myhub.mybatisboot.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author sussen
 * @Version 1.0.0
 * @Data 2020/1/3
 */
@Component
@Mapper
public interface UserDao {

    User findByUid(Long uid);

    void addUser(User user);

    //批量插入
    void addBatch(List<User> users);

    //批量更新
    void updStaBatch(@Param(value = "status") String status, @Param(value = "uids") List<Long> uids);

    //根据id批量删除
    void delBatchByUid(@Param(value = "uids") List<Long> uids);

}
