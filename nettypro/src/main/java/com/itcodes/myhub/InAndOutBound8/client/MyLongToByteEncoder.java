package com.itcodes.myhub.InAndOutBound8.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyLongToByteEncoder   自定义 long转byte 编码器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/24
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder 编码器被调用");
        System.out.println("msg:" + msg);
        out.writeLong(msg);
    }
}
