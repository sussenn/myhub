package com.itcodes.myhub.nettybuf2;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @ClassName NettyByteBufDemo01
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/22
 */
public class NettyByteBufDemo01 {
    public static void main(String[] args) {
        /*
         * netty的缓冲区不需要进行读写转换flip(). 底层维护了readerIndex(下一个读取的位置)和writerIntex(下一个写入的位置)
         */
        //创建长度为10的buf. 底层是数组,即byte[10]
        ByteBuf buf = Unpooled.buffer(10);
        buf.writeByte(666); //写入数据
        byte num = buf.readByte();  //读取数据
        //返回数组长度
        int len = buf.capacity();
//--------------------------------------------------------------
        ByteBuf bufStr = Unpooled.copiedBuffer("hello,world!", Charset.forName("utf-8"));
        //是否生成数组. 即是否有内容
        if (bufStr.hasArray()){
            byte[] content = bufStr.array();
            String contentStr = new String(content, Charset.forName("utf-8"));

            int offset = bufStr.arrayOffset();  //偏移量
            int readerIndex = bufStr.readerIndex(); //当前读取指向索引 0
            int writerIndex = bufStr.writerIndex(); //写操作指向索引 12
            int cap = bufStr.capacity();    //创建数组的容量 36
            int lens = bufStr.readableBytes();  //当前实际可读数组长度 12 ()

            //从索引1开始读,总共读取4位. ello
            CharSequence text = bufStr.getCharSequence(1, 4, Charset.forName("utf-8"));
        }
    }
}
