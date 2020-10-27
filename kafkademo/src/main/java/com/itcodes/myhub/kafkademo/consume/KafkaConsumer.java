package com.itcodes.myhub.kafkademo.consume;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

/**
 * @ClassName KafkaConsumer     消费端
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/17
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    public void linster(org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer) {
        while (true) {
            //循环监听
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            if (records != null && records.count() > 0) {
                //遍历监听到的消息
                for (ConsumerRecord<String, String> record : records) {
                    //监听到消息的key,value
                    String recordKey = record.key();
                    String recordValue = record.value();
                    //将消息转换json格式
                    try {
                        JsonNode jsonNode = objectMapper.readTree(recordValue);
                        //TODO 逻辑处理...

                    } catch (IOException e) {
                        log.error("json转换异常", e);
                    }
                }
            }
        }
    }
}
