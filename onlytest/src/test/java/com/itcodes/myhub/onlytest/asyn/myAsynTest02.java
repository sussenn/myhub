package com.itcodes.myhub.onlytest.asyn;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import com.itcodes.myhub.onlytest.service.MyAsynTest01Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName myAsynTest02
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/3
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
@Slf4j
public class myAsynTest02 {

    @Autowired
    private MyAsynTest01Service asynTest01Service;

    @Test
    public void test00(){

        asynTest01Service.doTaskA();
        asynTest01Service.doTaskB();
        asynTest01Service.doTaskC();
        asynTest01Service.doTaskD();
        asynTest01Service.doTaskE();
        asynTest01Service.doTaskF();
        asynTest01Service.doTaskG();
    }
}
