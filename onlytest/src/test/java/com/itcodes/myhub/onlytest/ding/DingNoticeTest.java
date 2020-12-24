package com.itcodes.myhub.onlytest.ding;

import com.itcodes.myhub.onlytest.OnlytestApplication;
import com.itcodes.myhub.onlytest.service.DingNotice;
import com.taobao.api.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DingNoticeTest
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/12/16
 */
@SpringBootTest(classes = OnlytestApplication.class)
@RunWith(value = SpringRunner.class)
public class DingNoticeTest {

    @Autowired
    private DingNotice dingNotice;

    @Test
    public void test00() throws ApiException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        dingNotice.sendAddGoodsNotice(format, "波士顿机器狗", 10);
    }
}
