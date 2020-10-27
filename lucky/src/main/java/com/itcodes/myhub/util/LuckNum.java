package com.itcodes.myhub.util;

import com.itcodes.myhub.lucky.pojo.Luckball;

import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName LuckNum
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/22
 */
public class LuckNum {

    public static Luckball meetLuck(){

        Integer[] redArr = new Integer[6];
        int blue;

        Random random = new Random();
        //生成一位蓝色号码 1-16
        blue = random.nextInt(16) + 1;

        //生成6位红色号码 1-33
        for (int i = 0; i < redArr.length; i++) {
            redArr[i] = random.nextInt(33) + 1;
            for (int s = 0; s < i; s++) {
                //如果重复,重新生成
                if (redArr[s] == redArr[i]){
                    i--;
                    break;
                }
            }
        }

        //排序
        Arrays.sort(redArr);
        //组装
        Luckball luckball = new Luckball();
        luckball.setRedLuck1(redArr[0] < 10 ? "0" + redArr[0] : redArr[0] + "");
        luckball.setRedLuck2(redArr[1] < 10 ? "0" + redArr[1] : redArr[1] + "");
        luckball.setRedLuck3(redArr[2] < 10 ? "0" + redArr[2] : redArr[2] + "");
        luckball.setRedLuck4(redArr[3] < 10 ? "0" + redArr[3] : redArr[3] + "");
        luckball.setRedLuck5(redArr[4] < 10 ? "0" + redArr[4] : redArr[4] + "");
        luckball.setRedLuck6(redArr[5] < 10 ? "0" + redArr[5] : redArr[5] + "");
        luckball.setBlueLuck(blue < 10 ? "0" + blue : blue + "");

        return luckball;
    }
}
