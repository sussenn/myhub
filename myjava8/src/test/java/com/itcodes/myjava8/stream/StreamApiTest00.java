package com.itcodes.myjava8.stream;

import com.itcodes.myjava8.MyJava8Application;
import com.itcodes.myjava8.lambda.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class StreamApiTest00 {
    /**
     * 一. 创建流
     * 二. 中间操作
     * 三. 终止操作
     *
     * stream流的创建
     * 1.stream()方法            串行流
     * 2.parallelStream()方法    并行流
     */

    @Test
    public void test00() {
        //1.Collection 系列集合 获取流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.Arrays的静态方法获取 数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.Stream的静态方法of() 获取
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.无限流
        //4.1用于迭代 的无限流
        //一.创建流     从0(种子)开始无限+2
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        //二.中间操作    生产10个
        stream4.limit(10)
                //三.终止操作    即输出
                .forEach(System.out::println);

        //4.2生成 无限流
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }
}
