package com.itcodes.myhub.mybatisboot;

import com.itcodes.myhub.mybatisboot.dao.UserDao;
import com.itcodes.myhub.mybatisboot.pojo.entity.User;
import com.itcodes.myhub.util.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName MybatisTest
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/3
 */
@SpringBootTest(classes = MyBatisBootApplication.class)
@RunWith(value = SpringRunner.class)
public class MybatisTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;


    //新增用户
    @Test
    public void addUser() {
        User user = new User();
        user.setUid(idWorker.nextId());
        user.setUname("Tom");
        user.setSex("男");
        user.setAge(28);
        user.setStatus("0");
        //String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setCreateTime(new Date());

        userDao.addUser(user);
    }

    //批量新增
    @Test
    public void addBatch() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setUid(idWorker.nextId());
            user.setUname("Test" + i);
            user.setSex("女");
            user.setAge(i);
            user.setStatus("1");
            user.setCreateTime(new Date());
            users.add(user);
        }
        userDao.addBatch(users);
    }

    @Test
    public void updBatchStatus() {
        ArrayList<Long> uids = new ArrayList<>();
        uids.add(6612257702054072331L);
        uids.add(6612257702054072332L);
        uids.add(6612257702054072333L);
        uids.add(6612257702054072334L);
        uids.add(6612257702054072335L);
        uids.add(6612257702054072336L);
        uids.add(6612257702054072337L);
        uids.add(6612257702054072338L);
        uids.add(6612257702054072339L);

        userDao.updStaBatch("3", uids);
    }

    //批量删除
    @Test
    public void delBatch() {
        ArrayList<Long> uids = new ArrayList<>();
        uids.add(6612257702054072331L);
        uids.add(6612257702054072332L);
        uids.add(6612257702054072333L);
        uids.add(6612257702054072334L);
        uids.add(6612257702054072335L);
        uids.add(6612257702054072336L);
        uids.add(6612257702054072337L);
        uids.add(6612257702054072338L);
        uids.add(6612257702054072339L);

        userDao.delBatchByUid(uids);

    }

    @Test
    public void findById() {
        User user = userDao.findByUid(6612257702054072339L);
        System.err.println(user);
    }

    @Test
    public void test() {
        String text = "龙岗派出所五号机房";
        //String[] s = text.split("所");
        //System.err.println(s[0].toString());
        int index = text.indexOf("所");
        String s = text.substring(0, index + 1);
        System.err.println(s);
    }

}
