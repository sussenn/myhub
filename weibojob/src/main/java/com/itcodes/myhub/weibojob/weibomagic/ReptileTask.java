package com.itcodes.myhub.weibojob.weibomagic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @ClassName ReptileTask   页面抓取，解析
 * @Author sussen
 * @Version 1.0
 * @Data 2019/9/8
 */
@Component
public class ReptileTask implements PageProcessor {

    //@Value(value = "${myreq.cookie}")
    //private String cookie;

    @Override
    public void process(Page page) {
        String imageUrl = page.getHtml().css("div.list_ul>div.list_li S_line1 clearfix>div.list_con>div.WB_media_wrap clearfix>div.media_box>a").links().toString();


        //把url添加到任务列表中
        //page.addTargetRequests(listUrl);

    }

    @Override
    public Site getSite() {
        return site;
    }

    private Site site = Site.me()
            //.addCookie("Cookie",cookie)     //添加cookie(需确认是在此处还是header添加)   TODO
            //设置请求头,伪造声明是浏览器请求
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.2595.400 QQBrowser/9.6.10872.400")
            .setCharset("UTF-8")
            .setTimeOut(3000)           //超时
            .setRetrySleepTime(3000)    //重试时间
            .setRetryTimes(3);          //重试次数
}
