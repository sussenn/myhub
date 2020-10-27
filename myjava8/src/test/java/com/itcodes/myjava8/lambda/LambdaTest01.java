package com.itcodes.myjava8.lambda;

import com.itcodes.myjava8.MyJava8Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @ClassName LambdaTest01
 *                  lambda表达式需要函数式接口支持.     函数式接口: 只有一个抽象方法的接口
 *                                                  注解FunctionalInterface    //声明此接口为 函数式接口
 *                  记忆: (箭头)左右遇一 括号省     左侧推断 类型省
 * @Author sussen
 * @Version 1.0
 * @Date 2020/5/25
 */
@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class LambdaTest01 {

    //1.无参,无返回值
    @Test
    public void test00() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        r.run();
        //--------------------------------------------
        Runnable r1 = () -> System.out.println("无参,无返回值");
        r1.run();
    }

    //2.有1个参数,无返回值
    //2.1 可以省略"()"
    @Test
    public void test01() {
        //Consumer<String> consumer = (x) -> System.out.println(x);
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("有参,无返回值");
    }

    //3. 有多个参数,有返回值 且右侧lambda体有多条执行语句,必须使用"{};"
    @Test
    public void test02() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("有多个参数,有返回值");
            return Integer.compare(x, y);
        };
    }

    //4. 有多个参数,有返回值 右侧lambda体只有一条执行语句时,return和"{}"可以省略
    @Test
    public void test03() {
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
    }
}
