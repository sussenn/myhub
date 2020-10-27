package com.itcodes.myhub.weibojob.weibomagic;

import com.itcodes.myhub.util.CrowProxyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @ClassName InitTask  爬虫任务初始化
 * @Author sussen
 * @Version 1.0
 * @Data 2019/9/8
 */
@Component
public class InitTask {

    @Value(value = "${myip.ips}")
    private String ips;
    @Value(value = "${myip.por}")
    private Integer por;
    @Value(value = "myurl.weibourl")
    private String weibourl;

    @Autowired
    private ImagesPipeline imagesPipeline;

    //每月每天12:20执行   cron = "0 3 11 ? * 3"
    @Scheduled(initialDelay = 1000,fixedDelay = 3000*1000)
    public void initTask() {
        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //设置动态转发代理，使用定制的ProxyProvider
        httpClientDownloader.setProxyProvider(CrowProxyProvider.from(new Proxy(ips, por)));

        Spider spider = Spider.create(new ReptileTask())
                .addUrl(weibourl)
                .addPipeline(new ConsolePipeline())    //控制台输出
                .addPipeline(imagesPipeline)          //自定义输出
                //数据过滤去重(布隆过滤器)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(50000)))
                .thread(10);                         //开启10个线程执行
        spider.run();       //启动
    }
}
