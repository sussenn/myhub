package com.itcodes.niobuffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MappedBufferDemo  MappedByteBuffer 让文件在直接内存(堆外内存)进行操作. [不必进行工作空间的拷贝]
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 */
public class MappedBufferDemo {
    public static void main(String[] args) throws IOException {
        //参数2: rw读写模式
        RandomAccessFile accessFile = new RandomAccessFile("a.txt", "rw");
        //获取通道
        FileChannel fileChannel = accessFile.getChannel();
        //参数1:使用读写模式 参数2:修改操作的起始位置  参数3:自定义设置文件映射到内存的大小.即此次操作的范围大小,能修改最多5个字节
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        //修改文件资源    修改0位的字节为Z
        mappedByteBuffer.put(0, (byte) 'Z');

        accessFile.close();
    }
}
