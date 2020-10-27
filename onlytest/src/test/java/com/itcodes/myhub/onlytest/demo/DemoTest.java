package com.itcodes.myhub.onlytest.demo;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

/**
 * @ClassName DemoTest
 * @Author sussen
 * @Version 1.0
 * @Date 2020/5/15
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class DemoTest {
    @Test
    public void test00() {

        Scanner input = new Scanner(System.in);

        //总价
        int total = Integer.parseInt(input.nextLine());
        //物价
        String price = input.nextLine();
        String[] priceArray = price.split(" ");
        int[] arrs = new int[priceArray.length];

        for (int i = 0; i < priceArray.length; i++) {
            arrs[i] = Integer.parseInt(priceArray[i]);
        }
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = 0; j < arrs.length - 1; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int team = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = team;
                }
            }
        }
        int totals = 0;
        for (int arr : arrs) {
            totals += arr;
            if (totals > total) {
                totals = totals - arr;
            }
        }
        System.out.println(totals);
    }
}
