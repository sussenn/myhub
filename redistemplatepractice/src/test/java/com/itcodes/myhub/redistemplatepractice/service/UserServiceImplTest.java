package com.itcodes.myhub.redistemplatepractice.service;

import com.itcodes.myhub.redistemplatepractice.RedisTemplatePracticeApplication;
import com.itcodes.myhub.redistemplatepractice.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = RedisTemplatePracticeApplication.class)
@RunWith(value = SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * String 1
     */
    @Test
    public void test00() {
        // set
        redisTemplate.opsForValue().set("key:1000", "SUCCESS", 10, TimeUnit.SECONDS);
        // get
        String suc = (String) redisTemplate.opsForValue().get("key:1000");
    }

    /**
     * String 2
     */
    @Test
    public void test01() {
        // set
        redisTemplate.boundValueOps("key:1000").set("userInfo", 2, TimeUnit.MINUTES);
        // get
        String userInfo = (String) redisTemplate.boundValueOps("key:1000").get();

    }

    /**
     * Hash
     */
    @Test
    public void test02() {
        User user = userService.findById(10000L);
        Long id = user.getId();

        // hash 存入redis
        redisTemplate.boundHashOps("redisTem:user:me:key100").put(id, user);
        // 设置有效期
        redisTemplate.expire("redisTem:user:me:key100", 10, TimeUnit.MINUTES);
        // 根据key获取
        User userGet = (User) redisTemplate.boundHashOps("redisTem:user:me:key1").get(id);

        // 根据key删除
        //redisTemplate.boundHashOps("redisTem:user:me:").delete(id);
    }

    /**
     * 获取当前key下所有数据
     */
    @Test
    public void test07() {
        List<User> userList = redisTemplate.boundHashOps("redisTem:user:me:key100").values();

    }

    /**
     * 计数器
     */
    @Test
    public void test04() {
        Long len = redisTemplate.boundHashOps("key:2000").increment("skuId", 1);
        if (len > 1) {
            System.err.println("重复数据");
        }
    }

    /**
     * List
     */
    @Test
    public void test05() {
        // 放入所有数据 (若放入List对象,则只作为一个单位存储. 即弹出时会将List整个弹出)
        //String[] ids = new String[]{"a", "b", "c"};
        //Long len1 = redisTemplate.boundListOps("skuId3000").leftPushAll(ids);

        Long len1 = redisTemplate.boundListOps("skuId3000").leftPushAll("1号", "2号", "3号");
        System.err.println(len1);

        // 左进+1
        Long len2 = redisTemplate.boundListOps("skuId3000").leftPush("4号");
        System.err.println(len2);
        // 右出-1
        Object skuId = redisTemplate.boundListOps("skuId3000").rightPop();
        System.err.println(skuId);
    }

    /**
     * key不存在则插入成功 (可当作一个锁)
     */
    @Test
    public void test03() {
        Boolean flg = redisTemplate.opsForValue().setIfAbsent("lock-1000", "SUCCESS", 30, TimeUnit.SECONDS);
        if (!flg) {
            System.err.println("重复数据");
        }
    }

    /**
     * 模糊匹配key,批量删除
     */
    @Test
    public void test06() {
        Set<String> keys = redisTemplate.keys("key:*");
        redisTemplate.delete(keys);
    }

}