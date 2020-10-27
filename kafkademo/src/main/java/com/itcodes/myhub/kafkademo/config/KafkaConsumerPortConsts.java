package com.itcodes.myhub.kafkademo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName KafkaConsumerPortConsts   Kafka生产者端口信息配置
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/17
 */
@Configuration
@ConfigurationProperties(prefix = "producer")
@PropertySource("classpath:kafkaconfig/kafka.properties")
@Data
public class KafkaConsumerPortConsts {
    private List<Map<String, String>> portconst;
}
