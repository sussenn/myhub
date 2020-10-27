package com.itcodes.myhub.kafkademo.config;

import com.itcodes.myhub.kafkademo.KafkaDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = KafkaDemoApplication.class)
@RunWith(value = SpringRunner.class)
public class ServerIdConstTest {

    @Test
    public void test00(){
        boolean succ = ServerIdConst.containId("aaaaa");
        System.out.println(succ);

    }
}