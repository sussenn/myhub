package com.itcodes.myhub.syslog.service;

import com.itcodes.myhub.syslog.annotation.SysLog;
import com.itcodes.myhub.syslog.dao.UserDao;
import com.itcodes.myhub.syslog.exception.XdfException;
import com.itcodes.myhub.syslog.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserService
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @SysLog("批量新增用户")
    public void addBatch(List<User> userList) {
        userDao.addBatch(userList);
    }

    @SysLog("根据用户id查询")
    public User findById(String id) {
        try {
            int i = 10 /0;
        	} catch (Exception e) {
        		throw new XdfException("这是啥");
        	}
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

}
