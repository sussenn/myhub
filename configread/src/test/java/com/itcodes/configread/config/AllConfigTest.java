package com.itcodes.configread.config;

import com.itcodes.configread.ConfigReadAppliaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ConfigReadAppliaction.class)
@RunWith(value = SpringRunner.class)
public class AllConfigTest {

    @Autowired
    private AllConfig allConfig;

    @Autowired
    private BaseConfig baseConfig;

    @Autowired
    private Environment env;

    /*@Value("#{application.initpar.wx.appid}")
    private String appid;*/

    @Value("#{'${testpar.alipay.keys}'.split(',')}")
    //@Scheduled(cron = "${cron.myjob}") 定时任务也可以通过${}获取配置文件变量
    private List<String> keys;

    //这是读取 properties 文件Map,List类型数据
    @Test
    public void testProperties() {
        Map<String, String> usermap = allConfig.getUsermap();
        System.out.println(usermap);

        List<String> list = allConfig.getMylist();
        System.out.println(list);
    }

    @Test
    public void testEnv() {
        String appid = env.getProperty("testpar.wxparam.appid");
        System.out.println(appid);
    }

    //这是@value截取","取出多个配置文件变量作list使用
    @Test
    public void testValue() {
        keys.forEach(System.out::println);
        System.out.println("还是 " + keys.get(0));
    }

    //这个写法把所有@value配置文件变量集中在一个类中处理了 可以直接用静态方法取出变量
    @Test
    public void testInitPar() {
        System.out.println(InitParamConfig.APPID);
        System.out.println(InitParamConfig.OPENID);
    }

    @Test
    public void testContainMap() {
        System.out.println(allConfig.containMap("xxx"));
    }

    @Test
    public void testBase() {
        String priKey = baseConfig.getPriKey();
        String pubKey = baseConfig.getPubKey();
        long tokenExpiration = baseConfig.getTokenExpiration();
        System.out.println(tokenExpiration);
        System.out.println(priKey);
        System.out.println(pubKey);
    }
}