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

/**
 * @ClassName StreamApiTest02
 * @Author sussen
 * @Version 1.0
 * @Date 2020/5/25
 */
@SpringBootTest(classes = MyJava8Application.class)
@RunWith(value = SpringRunner.class)
public class StreamApiTest02 {

    /**
     * 中间操作
     * 映射
     * map      接收lambda,将元素转换成其他形式或提取信息.接收一个函数作为参数,该函数会被应用到每个元素上,并将其映射为一个新的元素
     * faltMap  接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
     * <p>
     * [解读]: map是将整个对象转换成流,faltMap是将对象中每个元素单独转换成流.  参考test04
     */
    //数据
    private List<Employee> empList = Arrays.asList(
            new Employee("张三", 58, 6666.00),
            new Employee("李四", 28, 8888.00),
            new Employee("王五", 38, 2222.00),
            new Employee("赵六", 18, 3333.00),
            new Employee("赵六", 18, 3333.00)
    );

    //数据2
    private List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd");

    @Test
    public void test00() {

        strList.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);
    }

    @Test
    public void test01() {
        empList.stream()
                .map(employee -> employee.getName())
                .forEach(System.out::println);
    }

    //----------------------------------------------------------
    public static Stream<Character> filterChar(String str) {
        List<Character> clist = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            clist.add(ch);
        }
        return clist.stream();
    }


    //filterChar方法返回的是Stream<Character>,map方法返回的是Stream<Stream<Character>>流,    所以需要遍历2次
    @Test
    public void test02() {
        Stream<Stream<Character>> myStream = strList.stream()
                //类::静态方法名
                .map(StreamApiTest02::filterChar);//{{a,a,a},{b,b,b}...}

        myStream.forEach(sm -> sm.forEach(System.out::println));
    }

    //flatMap返回的是Stream<Character>流.    只需遍历一遍即可
    @Test
    public void test03() {
        Stream<Character> myStream = strList.stream()
                .flatMap(StreamApiTest02::filterChar);//{a,a,a,b,b,b...}

        myStream.forEach(System.out::println);
    }

    //map和faltMap的区别类似add()和addAll()
    @Test
    public void test04() {
         List tetList = new ArrayList();
         tetList.add(11);
         tetList.add(22);

         tetList.add(strList);      //[11, 22, [aaa, bbb, ccc, ddd]]
         //tetList.addAll(strList);   //[11, 22, aaa, bbb, ccc, ddd]
        System.out.println(tetList);
    }
}

