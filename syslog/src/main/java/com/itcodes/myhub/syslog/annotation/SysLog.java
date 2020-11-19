package com.itcodes.myhub.syslog.annotation;

import java.lang.annotation.*;

//方法上使用
@Target(ElementType.METHOD)
//运行时生效
@Retention(RetentionPolicy.RUNTIME)
//自定义注解是能随着被定义的java文件生成到JavaDoc文档中
@Documented
public @interface SysLog {
    /**
     * 描述
     */
    String value();
}
