package com.itcodes.configread.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AllConfig     allconfig.properties文件读取 Map List
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/6/30
 */
@Configuration
@ConfigurationProperties(prefix = "data")
@PropertySource("classpath:myconfig/allconfig.properties")
@Data
public class AllConfig {
    //变量名必须和allconfig.properties 中的usermap一致
    private Map<String,String> usermap = new HashMap<>();

    //变量名必须和allconfig.properties 中的mylist一致
    private List<String> mylist = new ArrayList<>();

    //如果配置map包含此key,则返回key对应value. 否则返回指定默认值
    public String containMap(String code){
        return usermap.getOrDefault(code, "未知");
    }

}
