package com.itcodes.myhub.onlytest.thread;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName Top01Test     多线程练习
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/28
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class Top01Test {

    /**
     * 先子线程循环10次,主线程循环100次 再子线程循环10次,主线程循环100次...如此循环50次
     */
    @Test
    public void topic() {
        //子线程和主线程整体各循环50次
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    sun(i);
                }
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            main(i);
        }
    }

    //条件
    private boolean term = true;

    //子线程循环10次
    private synchronized void sun(int i) {
        while (!term) {
            try {
                //为false则等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //为true运行
        for (int j = 1; j <= 10; j++) {
            System.err.println("子线程循环次数:" + j + "---整体循环次数:" + i);
        }
        //更改条件
        term = false;
        //唤醒
        this.notify();
    }

    private synchronized void main(int i) {
        while (term) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.err.println("主线程循环次数:" + j + "---整体循环次数:" + i);
        }
        term = true;
        this.notify();
    }
}
