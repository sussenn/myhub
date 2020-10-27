package com.itcodes.nio;

import java.util.Scanner;

/**
 * @ClassName DemoTest
 * @Author sussen
 * @Version 1.0
 * @Date 2020/5/15
 */
public class DemoTest {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        //总金额
        int total = Integer.parseInt(inp.nextLine());
        //物价集合
        String price = inp.nextLine();
        String[] priceArray = price.split(" ");
        int[] arrs = new int[priceArray.length];

        //单物价
        for (int i = 0; i < priceArray.length; i++) {
            arrs[i] = Integer.parseInt(priceArray[i]);
        }
        //排序
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = 0; j < arrs.length - 1; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int mid = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = mid;
                }
            }
        }
        int totals = 0;
        for (int arr : arrs) {
            //花费金额
            totals += arr;
            if (totals > total) {
                totals = totals - arr;
            }
        }
        System.out.println(totals);
    }
}
