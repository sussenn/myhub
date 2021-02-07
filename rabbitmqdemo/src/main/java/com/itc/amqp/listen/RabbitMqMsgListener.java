package com.itc.amqp.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sussenn
 * @version 1.0.0
 * @className RabbitMqMsgListener
 * @date 2021/2/5
 */
@Component
@RabbitListener(queues = {"itc.demoMes"})
public class RabbitMqMsgListener {

    @RabbitHandler
    public void getDemoMsg(String id) {

        //int a = 10 / 0;
        System.err.println("监听到消息: " + id);
    }
}
