package com.itcodes.myhub.syslog.dao;

import com.itcodes.myhub.syslog.SysLogApplication;
import com.itcodes.myhub.syslog.pojo.SysLogVo;
import com.itcodes.myhub.syslog.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SysLogApplication.class)
@RunWith(value = SpringRunner.class)
public class SysLogVoDaoTest {

    @Resource
    private SysLogDao sysLogDao;
    @Resource
    private UserDao userDao;

    @Test
    public void test00() {
        SysLogVo sysLog = new SysLogVo();
        sysLog.setMethodName("test1");
        sysLog.setStartTime(new Date());
        sysLog.setEndTime(new Date());

        sysLogDao.addSysLog(sysLog);
    }

    @Test
    public void test01() {
        //SysLogVo test = sysLogDao.findByName("test");
        //System.err.println(test);
    }

    @Test
    public void test02() {
        User user = userDao.findById("1");
        System.err.println(user);
    }

    @Test
    public void test03() {
        List<User> list = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("mark"+i);
            user.setPassword("xxx");
            user.setSex(1);
            list.add(user);
        }
        userDao.addBatch(list);
    }

    @Test
    public void test04() {
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("3");
        list.add("4");

        userDao.updateBatch(0,list);
    }
}