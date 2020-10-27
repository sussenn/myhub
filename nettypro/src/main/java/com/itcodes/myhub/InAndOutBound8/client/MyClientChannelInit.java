package com.itcodes.myhub.InAndOutBound8.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName MyClientChannelInit
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/24
 */
public class MyClientChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //添加自定义出站编码器
        pipeline.addLast(new MyLongToByteEncoder());
        //添加自定义业务处理器
        pipeline.addLast(new MyClientHandler());
    }
}
