package com.itcodes.myhub.lucky.job;

import com.itcodes.myhub.lucky.pojo.Luckball;
import com.itcodes.myhub.lucky.service.LuckMail;
import com.itcodes.myhub.util.LuckNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName LuckJob
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/25
 */
@Component
public class LuckJob {

    @Autowired
    private LuckMail luckMail;

    /**
     * 每周一/三/五/六 18点发送推荐号码邮件
     */
    //@Scheduled(cron = "0 0 18 ? * MON,WED,FRI,SAT")
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 60 * 30)
    public void sendLuck() {

        Luckball luckball = LuckNum.meetLuck();
        luckMail.sendMailInfo("幸运推荐", luckball);

    }
}
