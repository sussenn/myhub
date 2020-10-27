package com.itcodes.myjava8.methodref;

import com.itcodes.myjava8.MyJava8Application;
import com.itcodes.myjava8.lambda.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class MethodRefTest00 {
    /**
     * 方法引用: 若右侧lambda体中的内容有方法已经实现,则可以使用"方法引用"语法
     * 1.  对象::实例方法名
     * 2.  类::静态方法名
     * 3.  类::实例方法名
     *
     * [总结] 需要=号左边(函数式接口)和右边(实例方法)  参数列表(个数,类型),返回值类型保持一致 才能使用
     */

    @Test
    public void test00() {
        //lambda写法
        Consumer<String> con = x -> System.out.println(x);

        //方法引用写法    对象::实例方法名
        PrintStream out = System.out;
        Consumer<String> con1 = out::print;
        con1.accept("abc");

        /*
         *[注意]: 需要实现的接口方法(Consumer<T>的accept方法)参数列表,返回值类型 必须和 被调用方法(println方法)一致
         *      即 "=号左右对称"
         */
    }

    @Test
    public void test01() {
        Employee emp = new Employee("tom", 18, 100);
        Supplier<String> sup = () -> emp.getName();
        System.out.println(sup.get());

        //方法引用写法    对象::实例方法名           getAge()无参,返回Integer, get()也是同样的条件!
        Supplier<Integer> sup1 = emp::getAge;
        System.out.println(sup1.get());
    }

    //==================================================================
    @Test
    public void test02() {
        //lambda写法
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        //方法引用写法    类::静态方法名
        Comparator<Integer> com1 = Integer::compare;
        //-1:表示p1<p2.   0:表示p1=p2.     1:表示p1>p2
        int compare = com1.compare(0, 2);
        System.out.println(compare);
    }

    //==================================================================
    @Test
    public void test03() {
        //lambda写法
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        //方法引用写法    类::实例方法名
        //返回值类型=号左右一致,但参数列表不同
        //[注意] 当p1作对象调用实例方法,传参p2时,可使用此写法
        BiPredicate<String, String> bp1 = String::equals;

    }
}
