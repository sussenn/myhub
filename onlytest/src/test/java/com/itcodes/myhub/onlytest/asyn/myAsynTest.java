package com.itcodes.myhub.onlytest.asyn;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import com.itcodes.myhub.onlytest.service.MyAsynTest00Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class myAsynTest {

    @Autowired
    private MyAsynTest00Service myAsynTest00Service;

    @Test
    public void test00() throws ExecutionException, InterruptedException {
        myAsynTest00Service.asyn00();
        myAsynTest00Service.asyn01("test");
        Future<String> future = myAsynTest00Service.asyn02(1000);
        System.err.println(future.get());
        myAsynTest00Service.asyn03();
    }

}