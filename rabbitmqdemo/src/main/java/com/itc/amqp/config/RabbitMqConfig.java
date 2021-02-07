package com.itc.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfig
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2021/2/5
 */
//@Configuration
public class RabbitMqConfig {

    // 首次启动, 即未创建exchange和队列时, 必须先初始化以下创建操作
    // 当存在exchange和queue时, 则无需以下操作
    /*public final static String demoMes = "itc.demoMes";

    @Bean
    public Queue demoQueue() {
        // true: 消息持久化
        return new Queue(demoMes, true);
    }

    @Bean
    TopicExchange demoExchange() {
        return new TopicExchange("itc.demoExchange");
    }

    @Bean
    public Binding bind2Exchange() {
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(demoMes);
    }*/

}
