package com.itcodes.myhub.emailaop.exception;

import java.io.Serializable;

/**
 * @ClassName XdfException  自定义异常
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/1
 */
public class MyException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 121994219683867566L;
    private Integer code = 20001;
    private String msg;

    //响应码默认20001
    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MyException(Integer code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
