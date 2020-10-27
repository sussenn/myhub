package com.itcodes.myhub.nettyhttp1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @ClassName NettyHttpServerInit   管道处理器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyHttpServerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //获取管道
        //ChannelPipeline是一个handler [List集合].双向链表. 处理出站(client->server)/入站(server->client)
        //内部是由多个ChannelHandlerContext组成的链表,每个HandlerContext关联一个ChannelHeandler(自定义的)处理器
        ChannelPipeline pipeline = ch.pipeline();
        //添加编解码器 [netty提供的http编解码器]
        //addLast()将 业务处理类添加到链表最后一位
        pipeline.addLast("myCodes",new HttpServerCodec());
        //添加自定义的处理器
        pipeline.addLast("myServerHandler",new NettyHttpServerHandler());
    }
}
