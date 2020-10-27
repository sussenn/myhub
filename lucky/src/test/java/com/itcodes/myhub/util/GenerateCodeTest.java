package com.itcodes.myhub.util;


import com.itcodes.myhub.lucky.LuckyApplication;
import com.itcodes.myhub.lucky.pojo.Luckball;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Random;

@SpringBootTest(classes = LuckyApplication.class)
@RunWith(value = SpringRunner.class)
public class GenerateCodeTest {

    @Test
    public void test00(){

    }

    @Test
    public void test01(){
        Integer[] redArr = new Integer[6];
        int blue;

        Random random = new Random();
        //生成一位蓝色号码 1-16
        blue = random.nextInt(16) + 1;

        //生成6位红色号码 1-33
        for (int i = 0; i < redArr.length; i++) {
            redArr[i] = random.nextInt(33) + 1;
            for (int s = 0; s < i; s++) {
                if (redArr[s].equals(redArr[i])){
                    System.err.println(redArr[s]);
                    System.err.println(redArr[i]);
                    i--;
                    break;
                }
            }
        }
        System.err.println(Arrays.toString(redArr));
        System.err.println(blue);
    }

    @Test
    public void test02(){
        Luckball luckball = LuckNum.meetLuck();
        System.err.println(luckball);
    }

}