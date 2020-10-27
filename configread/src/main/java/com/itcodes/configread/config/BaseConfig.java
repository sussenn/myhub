package com.itcodes.configread.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName BaseConfig
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/7/27
 */
@Configuration
@ConfigurationProperties(prefix = "sha-256-key")
@PropertySource("classpath:myconfig/allconfig.properties")
@Data
public class BaseConfig {
    private String priKey;
    private String pubKey;
    private long tokenExpiration;
}
