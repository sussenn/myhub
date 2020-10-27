package com.itcodes.configread.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitParamConfig   springboot启动即初始化的配置参数
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/6/30
 */
@Component
public class InitParamConfig implements InitializingBean {

    @Value("${initpar.wx.appid}")
    private String appid;
    @Value("${initpar.wx.openid}")
    private String openid;

    public static String APPID;
    public static String OPENID;

    @Override
    public void afterPropertiesSet() throws Exception {
        APPID = appid;
        OPENID = openid;
    }
}
