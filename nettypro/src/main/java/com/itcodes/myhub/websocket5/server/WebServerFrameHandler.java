package com.itcodes.myhub.websocket5.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDate;

/**
 * @ClassName WebServerFrameHandler 帧数据交互处理器
 *              TextWebSocketFrame: 文本帧
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/23
 */
public class WebServerFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取浏览器发送的消息
        String text = msg.text();
        ctx.writeAndFlush(new TextWebSocketFrame("服务器当前时间:[" + LocalDate.now() + "],收到消息:" + text));
    }

    //连接建立即触发的首个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取管道id    .asLongText()是唯一值  .asShortText()不是唯一值
        String longText = ctx.channel().id().asLongText();
    }

    //连接断开,则触发的方法
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //...
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //...
    }
}
