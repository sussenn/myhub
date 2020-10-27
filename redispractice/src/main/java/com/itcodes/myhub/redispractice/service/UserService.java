package com.itcodes.myhub.redispractice.service;

import com.itcodes.myhub.redispractice.config.RedisTemplateConfig;
import com.itcodes.myhub.redispractice.dao.UseresDao;
import com.itcodes.myhub.redispractice.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Author sussen
 * @Version 1.0
 * @Date 2020/3/19
 */
@Service
public class UserService {

    @Autowired
    private UseresDao useresDao;

    //value: 设置空间名 user:bySid:  :表示下一级目录
    //key: 用于设置在命名空间中的缓存key值，可以使用SpEL表达式定义
    //unless: 条件符合则不缓存   unless = "#result==null"
    @Cacheable(value = RedisTemplateConfig.REDIS_KEY_DATABASE,
            key = "'user:bySid:'+ #sid")
    public Users getUserBySid(String sid) {
        return useresDao.findBySid(sid);
    }

    public List<Users> getUserByName(String name) {
        return useresDao.findByName(name);
    }

    public Users getUserByPhone(String phone) {
        return useresDao.findByPhone(phone);
    }

}
