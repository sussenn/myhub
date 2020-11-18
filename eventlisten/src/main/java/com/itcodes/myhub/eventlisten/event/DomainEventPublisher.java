package com.itcodes.myhub.eventlisten.event;

import com.itcodes.myhub.eventlisten.config.DomainEventAsyncConfig;
import com.itcodes.myhub.eventlisten.pojo.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName DomainEventPublisher  事件发布者
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
@Component
public class DomainEventPublisher implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(DomainEventPublisher.class);

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


    /**
     * 异步发布事件
     *
     * @param event
     */
    @Async(DomainEventAsyncConfig.DOMAIN_EXECUTOR)
    public void publishEvent(DomainEvent<?> event) {
        //发布事件
        context.publishEvent(event);

        //这个if只是优化 (无需入参,响应更快)
        //if (logger.isWarnEnabled()) {
        //    logger.warn("DomainEventPublish 事件发布者触发,eventMsg:{}", objectMapper.writeValueAsString(event));
        //}
    }
}
