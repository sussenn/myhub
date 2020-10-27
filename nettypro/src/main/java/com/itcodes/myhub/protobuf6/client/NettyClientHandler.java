package com.itcodes.myhub.protobuf6.client;

import com.itcodes.myhub.protobuf6.StudentPOJO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;

/**
 * @ClassName NettyClientHandler
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/21
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道初始化完成,即触发该方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送一个student对象给服务端
        StudentPOJO.Student wdc = StudentPOJO.Student.newBuilder().setId(4).setName("王大锤").build();
        ctx.writeAndFlush(wdc);

    }

    /**
     * 当通道发生读取事件即触发此方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String msgs = buf.toString(CharsetUtil.UTF_8);
        System.out.println("服务端消息:" + msgs);
        SocketAddress address = ctx.channel().remoteAddress();
        System.out.println("服务端地址:" + address);
    }

    /**
     * 发生异常的处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常信息
        cause.printStackTrace();
        //直接关闭通道
        ctx.close();
    }
}
