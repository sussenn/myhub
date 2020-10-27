package com.itcodes.myub.resthttp.httptest;

import com.itcodes.myhub.resthttp.RestHttpApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestHttpTest
 * @Author sussen
 * @Version 1.0
 * @Data 2020/1/15
 */
@SpringBootTest(classes = RestHttpApplication.class)
@RunWith(value = SpringRunner.class)
//此注解用于引入多个指定的配置文件
//@ContextConfiguration(classes = {RestTemplateConfig.class, HttpClientConfig.class})
public class RestHttpTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test00() {
        final String uri = "http://localhost:8080/employees";

        String result = restTemplate.getForObject(uri, String.class);

        Assert.assertEquals(true, result.indexOf("Lokesh") > 0);
    }
}
