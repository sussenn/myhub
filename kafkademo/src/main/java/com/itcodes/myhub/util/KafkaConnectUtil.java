package com.itcodes.myhub.util;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName KafkaConnectUtil  kafka连接配置
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/17
 */
@Data
public class KafkaConnectUtil {
    private final KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String,String> records;
    private final String topic = "uyun.indian.metric.data";
    private static final String GROUP_ID = "group";
    private String ip;

    public KafkaConnectUtil(String ip) {
        Properties properties = new Properties();
        this.ip = ip;
        properties.put("bootstrap.servers", ip + ":9192");
        properties.put("group.id", GROUP_ID);
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(properties);
        this.consumer.subscribe(Arrays.asList(this.topic));
    }
}
