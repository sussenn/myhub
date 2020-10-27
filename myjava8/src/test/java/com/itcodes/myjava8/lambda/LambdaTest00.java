package com.itcodes.myjava8.lambda;

import com.itcodes.myjava8.MyJava8Application;
import com.itcodes.myjava8.lambda.pojo.Employee;
import com.itcodes.myjava8.lambda.service.MyPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class LambdaTest00 {

    @Test
    public void test00() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }

    @Test
    public void test01() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    //--------------------------------------------------------------------
    //数据
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 18, 9999.00),
            new Employee("李四", 28, 8888.00),
            new Employee("王五", 38, 2222.00),
            new Employee("赵六", 48, 3333.00)
    );

    //过滤逻辑
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
        ArrayList<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.myComp(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }

    //1.匿名内部类 写法
    @Test
    public void test02() {
        List<Employee> list = filterEmployee(empList, new MyPredicate<Employee>() {
            @Override
            public boolean myComp(Employee employee) {
                return employee.getAge() >= 18;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //2.lambda表达式写法
    @Test
    public void test03() {
        List<Employee> list = filterEmployee(empList, (e) -> e.getAge() >= 28);
        list.forEach(System.out::println);
    }

    //3.streamApi写法     [不使用任何封装接口]
    @Test
    public void test04() {
        empList.stream()
                .filter((e) -> e.getSalary() >= 3000)
                .forEach(System.out::println);
    }

}