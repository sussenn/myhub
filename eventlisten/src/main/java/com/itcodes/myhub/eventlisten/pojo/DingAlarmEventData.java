package com.itcodes.myhub.eventlisten.pojo;

/**
 * @ClassName DingAlarmEventData    发送预警的实体(再包装)     <2
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
public class DingAlarmEventData extends DomainEvent<DingAlarmEventDto> {
    private static final long serialVersionUID = 4913449109677981946L;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DingAlarmEventData(DingAlarmEventDto source) {
        super(source);
    }
}
