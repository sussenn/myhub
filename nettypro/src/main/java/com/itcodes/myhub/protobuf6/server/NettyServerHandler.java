package com.itcodes.myhub.protobuf6.server;

import com.itcodes.myhub.protobuf6.StudentPOJO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServerHandler    自定义工作线程组 管道处理器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {
    //处理通道 读取客户发送数据 事件
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student msg) throws Exception {
        System.out.println("收到客户端消息:" + msg.getId() + "--" + msg.getName());
    }

    /**
     * 读取数据后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将读取的数据写入缓冲区并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 小客户", CharsetUtil.UTF_8));
    }


    /**
     * 发生异常的处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //直接关闭通道
        ctx.close();
    }
}
