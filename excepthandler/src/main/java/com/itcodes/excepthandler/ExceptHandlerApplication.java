package com.itcodes.excepthandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ExceptHandlerApplication      自定义异常,全局异常捕获测试
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@SpringBootApplication
public class ExceptHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExceptHandlerApplication.class, args);
    }
}