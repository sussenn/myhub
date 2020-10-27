package com.itcodes.niochannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName ChannelDemo   通道操作api
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 */
public class ChannelDemo {
    //1.使用通道写数据到文件
    public static void main(String[] args) throws Exception {
        String str = "hello world";
        //创建输出流
        FileOutputStream outputStream = new FileOutputStream("c:\\a.txt");
        //转换成通道
        FileChannel fileChannel = outputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将文本资源放入buffer
        byteBuffer.put(str.getBytes());
        //读写转换,进行读操作
        byteBuffer.flip();
        //将缓冲区数据写入到通道
        fileChannel.write(byteBuffer);

        //关闭流
        outputStream.close();
    }
    //2.使用通道读数据
    public static void channelRead() throws Exception {
        //创建文件输入流
        FileInputStream inputStream = new FileInputStream(new File("c:\\a.txt"));
        //转换成通道 资源数据已放在通道
        FileChannel fileChannel = inputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //读取通道里的数据到缓存区
        fileChannel.read(byteBuffer);

        //打印buffer缓冲区里的数据
        System.out.println(new String(byteBuffer.array()));

        //关闭流
        inputStream.close();
    }
}
