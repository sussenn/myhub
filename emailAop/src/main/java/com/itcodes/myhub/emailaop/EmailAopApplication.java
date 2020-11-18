package com.itcodes.myhub.emailaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName EmailAopApplication           1. 若springboot邮箱配置在-dev.yml, JavaMailSender 则无法注入?
 *                                                   1.1 可引入 spring-boot-start 依赖试试?
 *                                          2. 异常通知无法监测全局异常捕获类. 即全局异常捕获类注册通知无效
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/17
 */
@SpringBootApplication
public class EmailAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailAopApplication.class, args);
    }
    //@Bean
    //public JavaMailSenderImpl JavaMailSender(){
    //    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    //    mailSender.setHost("smtp.163.com");
    //    mailSender.setUsername("itcode123@163.com");
    //    mailSender.setPassword("RTJWLWKGQODYLTHL");
    //    return  mailSender;
    //}
}
