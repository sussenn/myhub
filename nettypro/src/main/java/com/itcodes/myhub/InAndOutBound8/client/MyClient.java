package com.itcodes.myhub.InAndOutBound8.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName MyClient  数据入站/出站顺序  自定义业务处理器-->编码器-->发送数据
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/24
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            //配置参数
            bootstrap.group(eventExecutors) //设置线程组
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInit());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 7000).sync();
            //监听通道关闭事件
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
