package com.itcodes.myhub.syslog.exception;

import com.itcodes.myhub.syslog.annotation.SysLog;
import com.itcodes.myhub.syslog.pojo.base.R;
import com.itcodes.myhub.syslog.pojo.base.StatusCode;
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

    @SysLog("全局异常捕获")
    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        logger.error("全局异常错误: ", e);
        return new R<>(false, StatusCode.ERROR, e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @SysLog("自定义异常捕获")
    @ExceptionHandler(XdfException.class)
    public R<XdfException> handlePException(XdfException e) {
        logger.error("自定义异常错误: {}", e.getMsg());
        return new R<>(false, e.getCode(), e.getMsg());
    }
}
