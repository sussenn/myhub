package com.itcodes.excepthandler.service;

import com.itcodes.excepthandler.exception.XdfException;
import com.itcodes.excepthandler.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@Service
public class UserService {

    public User findById(Long id) {
        if (id == 0) {
            //throw new XdfException(StatusCode.ERROR,"查无数据");
            throw new XdfException("查无数据");
        }
        User user = new User();
        user.setId(id);
        user.setUname("sussenn");
        user.setSex(1);
        return user;
    }
}
