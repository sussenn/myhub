package com.itcodes.myjava8.stream;

import com.itcodes.myjava8.MyJava8Application;
import com.itcodes.myjava8.lambda.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class StreamApiTest01 {
    /**
     * stream的中间操作
     * 筛选与切片
     * filter   接收lambda, 从流中排除某些元素
     * limit    截断流, 使元素不超过给定数量
     * skip(n)  跳过元素,返回一个不包含了前n个元素的流. 若元素不足n个,则返回空流. 与limit(n)互补
     * distinct 筛选,通过流所生成元素的hashcode()和equals() 去除重复元素
     */

    //数据
    private List<Employee> empList = Arrays.asList(
            new Employee("张三", 58, 6666.00),
            new Employee("李四", 28, 8888.00),
            new Employee("王五", 38, 2222.00),
            new Employee("赵六", 18, 3333.00),
            new Employee("赵六", 18, 3333.00)
    );

    //内部迭代: 迭代操作由Stream Api完成
    @Test
    public void test00() {
        //筛选出年龄>18
        empList.stream()
                //filter方法需要传参  Predicate<T>断言型接口   boolean test(T t);
                .filter((x) -> x.getAge() > 18)
                .forEach(System.out::println);
    }

    @Test
    public void test01() {
        empList.stream()
                //筛选
                .filter(x -> {
                    System.out.println("内部迭代...(仅迭代2次,称为: 短路)");
                    return x.getSalary() > 2222;
                })
                //截断
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test02() {
        empList.stream()
                .filter(x -> x.getSalary() > 2222)
                //跳过前2个元素
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test03() {
        empList.stream()
                .filter(x -> x.getSalary() > 2222)
                //去重    元素对象必须重写了hashCode和equals方法
                .distinct()
                .forEach(System.out::println);
    }
}
