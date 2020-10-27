package com.itcodes.myhub.kafkademo.listen;

import com.itcodes.myhub.kafkademo.config.KafkaConsumerPortConsts;
import com.itcodes.myhub.kafkademo.config.SpringContextUtil;
import com.itcodes.myhub.kafkademo.consume.KafkaConsumer;
import com.itcodes.myhub.util.KafkaConnectUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InitKafkaListener springboot运行即初始化Kafka监听
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/17
 */
@Component
public class InitKafkaListener implements CommandLineRunner {

    @Autowired
    private KafkaConsumerPortConsts consumerPortConsts;
    @Autowired
    private SpringContextUtil springContext;

    @Override
    public void run(String... args) throws Exception {
        //读取生产者端口配置信息 可配置多个    portconstList = [{key=LHGAFJ}, {value=68.74.8.79}]
        List<Map<String, String>> portconstList = consumerPortConsts.getPortconst();
        for (Map<String, String> portconst : portconstList) {
            //配置生产者ip 初始化Kafka
            KafkaConnectUtil connectUtil = new KafkaConnectUtil(portconst.get("value"));
            KafkaConsumer bean = springContext.getBean(KafkaConsumer.class);
            //监听到消息后 逻辑处理
            bean.linster(connectUtil.getConsumer());
        }

    }
}
