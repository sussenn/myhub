package com.itcodes.myjava8.lambda;

import com.itcodes.myjava8.MyJava8Application;
import com.itcodes.myjava8.lambda.pojo.Employee;
import com.itcodes.myjava8.lambda.service.MyFun;
import com.itcodes.myjava8.lambda.service.MyFunDemo01;
import com.itcodes.myjava8.lambda.service.MyFunDemo02;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class LambdaTest02 {

    //将MyFun接口作为参数.     调用时,使用lambda表达式实现具体操作方法
    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }

    //测试MyFun接口
    @Test
    public void test00() {
        Integer val = operation(100, x -> x * 2);
        System.out.println(val);
    }

    //----------------------------------------------------------------
    //数据
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 18, 9999.00),
            new Employee("李四", 38, 8888.00),
            new Employee("王五", 28, 2222.00),
            new Employee("赵六", 8, 3333.00)
    );

    //年龄的 定制排序
    @Test
    public void test01() {
        Collections.sort(empList, (e1, e2) -> {
            //如果年龄相同
            if (e1.getAge() == e2.getAge()) {
                //比较名字
                return e1.getName().compareTo(e2.getName());
            } else {
                //"-"表示倒序
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee employee : empList) {
            System.out.println(employee);
        }
    }

    //-----------------------------------------------------------------
    //将函数式接口作为传参
    public String strHandler(String s, MyFunDemo01 mf) {
        return mf.getValue(s);
    }

    @Test
    public void test02() {
        //去除空格
        String s1 = strHandler("   only测试-HOO321", s -> s.trim());
        System.out.println(s1);

        //转大写
        String s2 = strHandler("helloworld", s -> s.toUpperCase());
        System.out.println(s2);
    }

    //-----------------------------------------------------------------
    //将 有2个参数和1个返回值的 函数式接口作为传参
    public Long op(Long l1, Long l2, MyFunDemo02<Long, Long> mf) {
        return mf.getValue(l1, l2);
    }

    @Test
    public void test03() {
        Long aLong = op(100L, 20L, (x, y) -> x + y);
        System.out.println(aLong);
    }

}
