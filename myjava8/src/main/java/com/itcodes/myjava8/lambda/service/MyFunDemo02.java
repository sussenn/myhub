package com.itcodes.myjava8.lambda.service;

/**
 * @ClassName MyFunDemo02
 * @Author sussen
 * @Version 1.0.0
 * @Date 2020/5/25
 */
@FunctionalInterface
public interface MyFunDemo02<T, R> {
    R getValue(T t1, T t2);
}
