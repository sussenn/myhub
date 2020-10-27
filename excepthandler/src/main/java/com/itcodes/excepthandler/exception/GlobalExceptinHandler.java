package com.itcodes.excepthandler.exception;

import com.itcodes.excepthandler.pojo.R;
import com.itcodes.excepthandler.pojo.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptinHandler     全局异常处理
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptinHandler {
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new R(false, StatusCode.ERROR, e.getMessage());
    }

    /**处理自定义异常*/
    @ExceptionHandler(XdfException.class)
    public R<XdfException> handlePException(XdfException e){
        log.error("自定义异常错误:{}",e.getMsg());
        return new R<>(false,e.getCode(),e.getMsg());
    }
}
