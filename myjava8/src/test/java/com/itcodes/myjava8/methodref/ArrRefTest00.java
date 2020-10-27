package com.itcodes.myjava8.methodref;

import com.itcodes.myjava8.MyJava8Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Function;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class ArrRefTest00 {
    /**
     * 数组引用 语法  Type::new
     */
    @Test
    public void test00() {
        //lambda写法
        Function<Integer, String[]> fun = x -> new String[x];
        String[] strings = fun.apply(10);
        System.out.println(strings.length);

        //数组引用 语法
        Function<Integer, String[]> fun1 = String[]::new;
        System.out.println(fun1.apply(5).length);
    }
}
