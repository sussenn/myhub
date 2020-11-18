package com.itcodes.myhub.eventlisten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName EventListenApplication    springboot发布事件
 *                                          场景: 发布业务事件, 即刻被监听到, 然后被处理
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/18
 */
@SpringBootApplication
public class EventListenApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventListenApplication.class, args);
    }
}
