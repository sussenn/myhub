package com.itcodes.myhub.syslog.event;

import com.itcodes.myhub.syslog.config.EventAsyncConfig;
import com.itcodes.myhub.syslog.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName SysLogListen  自定义事件监听器
 *                          不使用@EventListener
 *                              实现 ApplicationListener<SysLogEvent>接口也可以,需要实现onApplicationEvent()方法
 *                                  public void onApplicationEvent(UserLogEvent userLogEvent) {
 *                                      Object source = event.getSource();
 *                                      if (source instanceof SysLogVo){
 *                                          sysLogService.addSysLog((SysLogVo) source);
 *                                      }
 *                                  }
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@Component

public class SysLogListen {

    private static final Logger logger = LoggerFactory.getLogger(SysLogListen.class);

    @Resource
    private SysLogService sysLogService;

    /**
     * 异步监听到发布的日志事件后, 将日志信息插入数据库
     *
     * @param event
     */
    @Async(EventAsyncConfig.EVENT_EXECUTOR)
    @EventListener(SysLogEvent.class)
    public void addSysLogEventHandler(SysLogEvent event) {
        sysLogService.addSysLog(event.getSysLogVo());
        //logger.warn("系统日志被监听到,执行方法:[{}] ", event.getSysLogVo().getMethodName());
    }
}
