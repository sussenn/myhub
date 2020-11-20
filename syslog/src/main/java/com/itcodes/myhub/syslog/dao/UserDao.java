package com.itcodes.myhub.syslog.dao;

import com.itcodes.myhub.syslog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@Repository
//@Mapper
public interface UserDao {

    User findById(String id);

    List<User> findAll();

    void addBatch(List<User> userList);

    void updateBatch(@Param("sex") int sex, @Param("ids") List<String> ids);
}
