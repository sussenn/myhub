package com.itcodes.myhub.syslog.aspect;

import com.itcodes.myhub.syslog.annotation.SysLog;
import com.itcodes.myhub.syslog.config.EventAsyncConfig;
import com.itcodes.myhub.syslog.event.SysLogEvent;
import com.itcodes.myhub.syslog.pojo.SysLogVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName SysLogAspect  系统日志 切面类
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/19
 */
@Component
@Aspect
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    //事件发布对象
    @Resource
    private ApplicationEventPublisher eventPublisher;

    //环绕通知
    //切入所有标有自定义注解@SysLog的方法
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint pjp, SysLog sysLog) throws Throwable {
        //获取类名
        String className = pjp.getTarget().getClass().getName();
        //方法名
        String methodName = pjp.getSignature().getName();
        String name = className + "." + methodName;
        //创建日志对象
        SysLogVo sysLogVo = new SysLogVo();
        //日志标题
        sysLogVo.setTitle(sysLog.value());
        //方法开始时间
        sysLogVo.setStartTime(new Date());
        //执行被切入的方法
        Object obj = pjp.proceed();
        //方法结束时间
        sysLogVo.setEndTime(new Date());
        sysLogVo.setMethodName(name);
        //创建日志事件对象
        SysLogEvent sysLogEvent = new SysLogEvent(sysLogVo);
        //发布日志事件    [异步无效]
        eventPublisher.publishEvent(sysLogEvent);
        //logger.warn("日志发布触发,触发方法:[{}]", name);
        return obj;
    }
}
