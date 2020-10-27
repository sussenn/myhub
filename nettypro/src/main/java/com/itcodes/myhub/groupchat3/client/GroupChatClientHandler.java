package com.itcodes.myhub.groupchat3.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName GroupChatClientHandler    自定义业务处理器(string)
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/22
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
