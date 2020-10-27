package com.itcodes.myjava8.lambda.service;

/**
 * @ClassName MyPredicate       策略设计模式
 * @Author sussen
 * @Version 1.0.0
 * @Date 2020/5/25
 */
@FunctionalInterface    //声明此接口为 函数式接口
public interface MyPredicate<T> {
    boolean myComp(T t);
}
