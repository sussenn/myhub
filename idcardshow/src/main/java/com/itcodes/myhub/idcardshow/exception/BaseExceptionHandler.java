package com.itcodes.myhub.idcardshow.exception;

import com.itcodes.myhub.idcardshow.pojo.dto.Result;
import com.itcodes.myhub.idcardshow.pojo.dto.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName BaseExceptionHandler      公共异常处理器
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/11
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

    /**处理自定义异常*/
    @ExceptionHandler(SuException.class)
    public Result handlePException(SuException e){
        log.error("自定义异常错误:{}",e.getMessage());
        return new Result(false,StatusCode.ACCESSERROR,e.getMessage());
    }

}
