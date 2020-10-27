package com.itcodes.myjava8.lambda.pojo;

import lombok.Data;

/**
 * @ClassName Employee
 * @Author sussen
 * @Version 1.0
 * @Date 2020/5/25
 */
@Data
public class Employee {

    private String name;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
