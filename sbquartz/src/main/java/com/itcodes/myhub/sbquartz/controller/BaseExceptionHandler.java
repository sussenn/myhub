package com.itcodes.myhub.sbquartz.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BaseExceptionHandler  统一异常处理器
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/22
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String error(Exception e){
        e.printStackTrace();
        return "定时任务异常";
    }
}
