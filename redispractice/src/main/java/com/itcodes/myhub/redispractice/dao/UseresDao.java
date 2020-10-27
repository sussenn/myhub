package com.itcodes.myhub.redispractice.dao;

import com.itcodes.myhub.redispractice.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName UseresDao
 * @Author sussen
 * @Version 1.0.0
 * @Date 2020/3/19
 */
public interface UseresDao extends JpaRepository<Users, String>{

    List<Users> findByName(String name);
    Users findByPhone(String phone);
    Users findBySid(String sid);
}
