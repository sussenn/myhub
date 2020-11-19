package com.itcodes.myhub.syslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName SysLogApplication     自定义系统日志注解   AOP + 事件发布 + 监听器
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SysLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysLogApplication.class, args);
    }
}
