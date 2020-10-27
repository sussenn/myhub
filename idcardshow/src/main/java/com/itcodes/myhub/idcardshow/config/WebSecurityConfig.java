package com.itcodes.myhub.idcardshow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebSecurityConfig 安全配置
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/22
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()    //请求授权
                .antMatchers("/**").permitAll() //放行所有
                .anyRequest().authenticated()   //没有匹配以上路径,用户被认证即可访问
                .and().csrf().disable();    //关闭跨域安全请求
    }
}
