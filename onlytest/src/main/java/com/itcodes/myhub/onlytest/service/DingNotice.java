package com.itcodes.myhub.onlytest.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName DingNoticeOfAddGoods
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/12/16
 */
@Component
@Slf4j
public class DingNotice {

    @Value("${ali.ding.robot.webhoot}")
    private String webhoot;

    @Value("${ali.ding.robot.sysurl}")
    private String sysurl;

    public void sendAddGoodsNotice(String date, String name, int num) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(webhoot);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(sysurl);
        link.setPicUrl("");
        link.setTitle("【库存预警】");
        link.setText(date + " 积分商城 [" + name + "]商品剩余" + num + "件，达到预警值，请及时补充库存，防止影响积分商城正常运作。");
        request.setLink(link);

        OapiRobotSendResponse response = client.execute(request);
        log.info("sendAddGoodsNotice() 钉钉机器人推送结果响应码: [{}]", response.getErrcode());
    }
}
