package com.itcodes.myjava8.methodref;

import com.itcodes.myjava8.MyJava8Application;
import com.itcodes.myjava8.lambda.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class BuildRefTest00 {
    /**
     * 构造器引用 语法     类::new
     * [注意] =号右侧使用的构造器是否有参,取决于=号左边函数式接口是否有参
     */
    @Test
    public void test00() {
        //lambda写法
        Supplier<Employee> sup = () -> new Employee();
        Employee employee = sup.get();

        //构造器引用语法   "=号左右一致",即无参,返回值类型为Employee
        Supplier<Employee> sup2 = Employee::new;
        Employee employee1 = sup2.get();

    }

    @Test
    public void test01() {
        //lambda写法
        Function<String, Employee> fu = s -> new Employee(s);

        //构造器引用语法  "=号左右一致",有1个参数,返回值类型为Employee
        Function<String, Employee> fu1 = Employee::new;
        Employee em1 = fu1.apply("cat");
        System.out.println(em1);

        //构造器引用语法  "=号左右一致",有2个参数,返回值类型为Employee
        BiFunction<String, Integer, Employee> fu2 = Employee::new;
        Employee em2 = fu2.apply("apache", 100);
        System.out.println(em2);
    }
}
