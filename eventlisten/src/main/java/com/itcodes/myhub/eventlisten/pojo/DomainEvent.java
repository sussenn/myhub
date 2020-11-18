package com.itcodes.myhub.eventlisten.pojo;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @ClassName DomainEvent   事件最终实体    <3
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
public class DomainEvent<T extends Serializable> extends ApplicationEvent {

    private static final long serialVersionUID = -8619567428904235958L;
    private String eventName;

    private String eventHandler;

    //事件内容(根据业务制定)
    private T eventData;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DomainEvent(T source) {
        //source 包含时间戳属性    (在ApplicationEvent)
        super(source);
        this.eventData = source;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(String eventHandler) {
        this.eventHandler = eventHandler;
    }

    public T getEventData() {
        return eventData;
    }

    public void setEventData(T eventData) {
        this.eventData = eventData;
    }
}
