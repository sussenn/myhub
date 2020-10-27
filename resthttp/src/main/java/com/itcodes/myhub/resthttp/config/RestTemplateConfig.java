package com.itcodes.myhub.resthttp.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestTemplateConfig    RestTemplate模板配置[配合HttpClientConfig]
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/15
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    CloseableHttpClient httpClient;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    /**
     * http请求工厂配置
     * 使用@Autowired注入的CloseableHttpClient实例来构建ClientHttpRequestFactory 该对象用于创建RestTemplate
     * HttpComponentsClientHttpRequestFactory是ClientHttpRequestFactory实现
     * 它使用Apache HttpComponents HttpClient创建请求
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }

    /**
     * 对线程的计划执行的支持
     * httpClient配置中使用了@Scheduled注解. 为此,在此必须添加对线程的计划执行的支持
     * 注意: 配合定时任务异步配置时,此bean可不必注入
     * @return
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolSched-");
        scheduler.setPoolSize(50);
        //设置线程池等待所有任务都完成再关闭
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池等待超时,否则强制关闭
        scheduler.setAwaitTerminationSeconds(30);
        return scheduler;
    }
}
