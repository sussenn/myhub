package com.itcodes.myhub.idcardshow.config;

import com.itcodes.myhub.idcardshow.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName ApplicationConfig 拦截器
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/22
 */
@Component
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)     //根据jwtInterceptor类拦截规则进行拦截
                .addPathPatterns("/**")               //拦截所有路径
                .excludePathPatterns("/admin/login");           //除了登录不拦截
    }
}
