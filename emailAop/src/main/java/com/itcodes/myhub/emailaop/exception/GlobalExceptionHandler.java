package com.itcodes.myhub.emailaop.exception;

import com.itcodes.myhub.emailaop.pojo.R;
import com.itcodes.myhub.emailaop.pojo.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler     全局异常处理
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new R(false, StatusCode.ERROR, e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MyException.class)
    public R<MyException> handlePException(MyException e) {
        logger.error("自定义异常错误:{}", e.getMsg());
        return new R<>(false, e.getCode(), e.getMsg());
    }
}
