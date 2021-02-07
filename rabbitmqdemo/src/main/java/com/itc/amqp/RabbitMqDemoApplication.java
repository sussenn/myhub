package com.itc.amqp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className RabbitMqDemoApplication
 * @author sussenn
 * @version 1.0.0
 * @date 2021/2/5
 */
@SpringBootApplication
@MapperScan("com.itc.amqp.dao")
public class RabbitMqDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqDemoApplication.class, args);
    }
}
