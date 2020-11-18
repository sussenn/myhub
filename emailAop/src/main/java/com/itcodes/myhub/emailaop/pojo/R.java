package com.itcodes.myhub.emailaop.pojo;

import java.io.Serializable;

/**
 * @ClassName R    响应结果封装的实体类
 * @Author sussenn
 * @Version 1.0
 * @Data 2020/7/1
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 2673673106773964606L;
    private boolean flag;
    private Integer code;
    private String message;
    private T data;

    public R(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public R(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
