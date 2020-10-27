package com.itcodes.myhub.onlytest.thread;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName Top02Test     多线程练习
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/28
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class Top02Test {

    @Test
    public void test00(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    out1("zhoshucheng");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    out2("ZHOUSHUCHENG");
                }
            }
        }).start();


    }

    private synchronized void out1(String name){
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.err.println(name.charAt(i));
        }
    }

    private synchronized void out2(String name){
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.err.println(name.charAt(i));
        }
    }
}
