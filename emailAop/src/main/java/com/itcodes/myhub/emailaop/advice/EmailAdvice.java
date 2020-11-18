package com.itcodes.myhub.emailaop.advice;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @ClassName EmailAdvice   邮件通知
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/17
 */
@Component
@Aspect
public class EmailAdvice {

    /**
     * @Tips: JavaMailSender显示未注入,但实际不影响正常功能.
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMail;
    @Value("${receiveEmail.qq}")
    private String receiveEmail;

    private SimpleMailMessage message = new SimpleMailMessage();

    //异常通知  监控UserService下所有方法抛出的异常
    @AfterThrowing(value = "execution(* com.itcodes.myhub.emailaop.service.UserService.*(..))", throwing = "e")
    public void showException(Throwable e) {
        sendMailInfo(e);
    }

    //注意: 全局异常类注册通知 不生效!
    //@AfterThrowing(value = "execution(* com.itcodes.myhub.emailaop.exception.GlobalExceptionHandler.*(..))", throwing = "e")
    //public void showException1(Throwable e) {
    //    sendMailInfo(e);
    //}

    private void sendMailInfo(Throwable e) {
        //发件邮件
        message.setFrom(sendMail);
        //收件邮箱
        message.setTo(receiveEmail);
        //邮件主题
        message.setSubject("异常通知");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(out));
        //邮件内容
        message.setText("异常信息以下: \n" + out.toString());
        javaMailSender.send(message);
    }
}
