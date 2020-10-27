package com.itcodes.niochannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName ChannelCopyDemo   使用同一个缓冲区进行copy读写操作
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 */
public class ChannelCopyDemo {
    public static void main(String[] args) throws Exception {
        //不写"c:"则操作的是项目根路径
        //输入流 操作a.txt
        FileInputStream inputStream = new FileInputStream("c:\\a.txt");
        FileChannel inChannel = inputStream.getChannel();

        //输出流 操作b.txt
        FileOutputStream outputStream = new FileOutputStream("c:\\b.txt");
        FileChannel outChannel = outputStream.getChannel();

        //创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){
            //清空缓冲区(将标识置0),以便循环读取
            byteBuffer.clear();
            //将(输入)通道数据读取到缓存区 -->a.txt
            int read = inChannel.read(byteBuffer);
            if (read == -1){
                break;
            }
            //读写转换
            byteBuffer.flip();
            //将缓冲区数据写出到(输出)通道 -->b.txt
            outChannel.write(byteBuffer);
        }

        inChannel.close();
        outChannel.close();
    }
}
