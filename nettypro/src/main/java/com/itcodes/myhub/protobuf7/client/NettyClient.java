package com.itcodes.myhub.protobuf7.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @ClassName NettyClient
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyClient {
    public static void main(String[] args){
        //循环事件组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            //配置参数
            bootstrap.group(eventExecutors) //设置线程组
                    .channel(NioSocketChannel.class)    //设置客户端通道实现
                    //设置 管道处理器
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //添加protobuf编码器
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            //使用自定义处理器
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });

            //启动客户端并连接服务端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
            //监听通道关闭事件
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭
            eventExecutors.shutdownGracefully();
        }
    }
}
