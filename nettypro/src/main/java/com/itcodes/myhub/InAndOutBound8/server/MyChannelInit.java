package com.itcodes.myhub.InAndOutBound8.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName MyChannelInit 自定义管道
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/24
 */
public class MyChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //添加自定义入站解码器
        pipeline.addLast(new MyByteToLongDecoder());
        //添加自定义业务处理器
        pipeline.addLast(new MyServerHandler());
    }
}
