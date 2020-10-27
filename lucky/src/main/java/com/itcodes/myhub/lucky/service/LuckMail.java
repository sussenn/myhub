package com.itcodes.myhub.lucky.service;

import com.itcodes.myhub.lucky.pojo.Luckball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @ClassName LuckMail
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/25
 */
@Service
public class LuckMail {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendMailName;
    @Value("${mymail.receivename}")
    private String recename;

    private SimpleMailMessage message = new SimpleMailMessage();

    public void sendMailInfo(String mailSub, Luckball luckball) {
        message.setFrom(sendMailName);
        message.setTo(recename);
        message.setSubject(mailSub);

        String text = "[" + luckball.getRedLuck1() +"] [" + luckball.getRedLuck2() + "] [" + luckball.getRedLuck3() + "] [" + luckball.getRedLuck4() + "] [" + luckball.getRedLuck5() + "] 【" + luckball.getBlueLuck() + "】";

        message.setText(text);
        javaMailSender.send(message);
    }
}
