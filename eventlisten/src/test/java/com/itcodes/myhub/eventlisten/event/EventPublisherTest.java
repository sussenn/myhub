package com.itcodes.myhub.eventlisten.event;

import com.itcodes.myhub.eventlisten.EventListenApplication;
import com.itcodes.myhub.eventlisten.pojo.DingAlarmEventData;
import com.itcodes.myhub.eventlisten.pojo.DingAlarmEventDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = EventListenApplication.class)
@RunWith(value = SpringRunner.class)
public class EventPublisherTest {

    @Autowired
    private DomainEventPublisher domainEventPublisher;


    /**
     * 1. 根据业务场景,调用domainEventPublisher.publishEvent()方法,异步走另一套业务逻辑
     * 2. 事件被监听到,即刻执行
     */
    @Test
    public void test00() {
        DingAlarmEventDto dingAlarmEventDto = new DingAlarmEventDto();
        dingAlarmEventDto.setTitle("预警标题");
        dingAlarmEventDto.setSubject("预警科目");
        dingAlarmEventDto.setMessage("预警内容");
        DingAlarmEventData alarmEventData = new DingAlarmEventData(dingAlarmEventDto);
        alarmEventData.setEventName("事件名...");
        alarmEventData.setEventHandler("事件头...");
        //发布事件...
        domainEventPublisher.publishEvent(alarmEventData);
    }

}