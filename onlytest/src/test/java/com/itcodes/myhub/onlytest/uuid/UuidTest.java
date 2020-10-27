package com.itcodes.myhub.onlytest.uuid;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import com.itcodes.myhub.onlytest.config.consts.TestIdConst;
import com.itcodes.myhub.onlytest.pojo.User;
import com.itcodes.myhub.onlytest.service.ForEachLogicService;
import com.itcodes.myhub.util.PatternKit;
import com.itcodes.myhub.util.UUID;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName UuidTest
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/3
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class UuidTest {

    @Autowired
    private ForEachLogicService logicService;

    @Test
    public void test04() {

    }

    @Test
    public void test03(){
        boolean flag = false;
        if (flag){
            System.out.println(flag);

        }
        if (!flag){
            System.err.println(!flag);
        }

    }

    @Test
    public void test02() {
        List<User> list1 = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            User user1 = new User();
            user1.setId(i + "");
            user1.setAge(i + "");
            user1.setAddr("aaa");
            list1.add(user1);
            System.out.println(user1);
        }

        List<User> list2 = new ArrayList<>();
        for (int i = 3; i < 6; i++) {
            User user2 = new User();
            user2.setId(i + "");
            user2.setAge(i + "");
            user2.setAddr("bbb");
            list2.add(user2);
            System.out.println(user2);
        }

        Set<User> userSet = logicService.logicHandle(list1, list2);
        System.err.println(userSet);

    }

    @Test
    public void test01() {
        boolean succ = TestIdConst.containId("aaaaa");
        System.err.println(succ);
        String value = TestIdConst.getValue("xxxxx");
        System.err.println(value);
    }

    @Test
    public void test00() {
        //String s = UUID.captchaChar(20);
        //System.err.println(s);

        //String s1 = UUID.captchaNumber(50);
        //System.err.println(s1);

        String s = UUID.captchaChar(18, true);
        System.err.println(s);

    }

    @Test
    public void patternKit() {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        strings.add("123");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.err.println(next);
        }
        CopyOnWriteArraySet<String> strings1 = new CopyOnWriteArraySet<>();

        //PatternKit patternKit = new PatternKit();
        //boolean idCard = PatternKit.isIdCard("36022219950609x");
        //boolean mobile = PatternKit.isMobile("18779816460");
        //System.err.println(mobile);
        boolean email = PatternKit.isEmail("test@gmail.com");
        System.err.println(email);
    }

}
