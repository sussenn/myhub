package com.itcodes.myhub.eventlisten.pojo;

import java.io.Serializable;

/**
 * @ClassName DingAlarmEventDto 发送预警的实体(根据自身业务制定)     <1
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
public class DingAlarmEventDto implements Serializable {

    private static final long serialVersionUID = -8065301538319830963L;
    private String title;

    private String subject;

    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DingAlarmEventDto{" +
                "title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
