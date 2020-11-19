package com.itcodes.myhub.syslog.pojo.base;

/**
 * 响应状态码
 */
public interface StatusCode {
    int OK = 20000;//成功
    int ERROR = 20001;//失败
    /**
     * 用户名或密码错误
     */
    int LOGINERROR = 20002;
    /**
     *  访问失败/权限不足
     */
    int ACCESSERROR = 20003;
    /**
     * 远程调用失败
     */
    int REMOTEERROR = 20004;
    /**
     * 重复操作
     */
    int REPERROR = 20005;
}
