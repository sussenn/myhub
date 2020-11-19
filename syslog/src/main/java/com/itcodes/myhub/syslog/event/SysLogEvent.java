package com.itcodes.myhub.syslog.event;

import com.itcodes.myhub.syslog.pojo.SysLogVo;

/**
 * @ClassName SysLogEvent   自定义事件
 *                          继承 ApplicationEvent 重写 UserLogEvent()方法也可以
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
public class SysLogEvent  {

    private final SysLogVo sysLogVo;

    public SysLogEvent(SysLogVo sysLogVo) {
        this.sysLogVo = sysLogVo;
    }

    SysLogVo getSysLogVo() {
        return sysLogVo;
    }
}
