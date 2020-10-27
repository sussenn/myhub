package com.itcodes.myhub.InAndOutBound8.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyByteToLongDecoder   自定义编码器
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/24
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder 被调用");
        //如果入站字节数>=8,则放入list. long的长度为8
        if (in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
