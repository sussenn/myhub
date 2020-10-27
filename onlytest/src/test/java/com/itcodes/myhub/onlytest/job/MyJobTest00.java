package com.itcodes.myhub.onlytest.job;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MyJobTest00
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class MyJobTest00 {

    @Autowired
    private MyJob00 myJob00;

    @Test
    public void test00(){
        myJob00.myJobA();
        myJob00.myJobB();
        myJob00.myJobC();
        myJob00.myJobD();
        myJob00.myJobE();
        myJob00.myJobF();
        myJob00.myJobG();
    }
}
