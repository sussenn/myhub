package com.itcodes.myjava8.lambda;

import com.itcodes.myjava8.MyJava8Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName LambdaTest03  JDK8 内置4大核心函数式接口
 * 1. Consumer<T> :消费型接口
 * void accept(T t);
 * 2. Supplier<T> :供给型接口
 * T get();
 * 3. Function<T, R> :函数型接口
 * R apply(T t);
 * 4. Predicat<T> :断言型接口
 * boolean test(T t);
 * @Author sussen
 * @Version 1.0
 * @Date 2020/5/25
 */
@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class LambdaTest03 {

    //=====================Consumer<T> :消费型接口   1参,无返=========================================
    //将消费型接口 传参
    public void doPlay(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test00() {
        doPlay(1000, m -> System.out.println("消费金额:" + m));
    }

    //======================Supplier<T> :供给型接口  1参,有返========================================
    //将接口 作为参数传递
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    @Test
    public void test01() {
        //生产100以内的随机数, 取出10个
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        numList.forEach(n -> System.out.println(n));
    }

    //======================Function<T, R> :函数型接口   1参,有返========================================
    //将接口 作为参数传递
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    @Test
    public void test02() {
        String str = strHandler("   hello   ", s -> s.trim());
        System.out.println(str);
    }

    //======================Predicat<T> :断言型接口  1参,Boolean========================================
    //将接口 作为参数传递
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)){
                strList.add(s);
            }
        }
        return strList;
    }

    @Test
    public void test03() {
        List<String> list = Arrays.asList("hello","abcd","world");
        //List<String> filterList = filterStr(list, x -> x.equals("hello"));
        List<String> filterList = filterStr(list, x -> x.length() > 4);
        filterList.forEach(n -> System.out.println(n));
    }
}
