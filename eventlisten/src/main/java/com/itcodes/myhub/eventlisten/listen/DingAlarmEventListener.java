package com.itcodes.myhub.eventlisten.listen;

import com.itcodes.myhub.eventlisten.pojo.DingAlarmEventData;
import com.itcodes.myhub.eventlisten.pojo.DingAlarmEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName DingAlarmEventListener    事件监听器
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
@Component
//如不实现ApplicationListener接口,亦可使用@EventListener注解
//public class DingAlarmEventListener implements ApplicationListener<DingAlarmEventData> {
public class DingAlarmEventListener {
    private static final Logger logger = LoggerFactory.getLogger(DingAlarmEventListener.class);

    //@Override
    @EventListener
    public void onApplicationEvent(DingAlarmEventData event) {
        try {
            //这个if只是优化 (无需入参,响应更快)
            //if (logger.isWarnEnabled()) {
            //    logger.warn("DomainEventListener 事件监听器触发,eventMsg:{}", objectMapper.writeValueAsString(event));
            //}
            DingAlarmEventDto eventData = event.getEventData();
            if (null == eventData) {
                logger.warn("domainEventData 预警的实体为空");
                return;
            }
            //TODO 处理监听到事件后的业务
            System.err.println("监听到:" + eventData.getMessage());

        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
