package com.itcodes.niobuffer;

import java.nio.IntBuffer;

/**
 * @ClassName BufferDemo  buffer的操作示例
 *     private int mark = -1;       标记
 *     private int position = 0;    下一个将被操作元素的下标(最大等于limit)
 *     private int limit;           缓冲区的终点
 *     private int capacity;        数组容量
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/15
 */
public class BufferDemo {
    public static void main(String[] args) {
        //创建可以存放5个int类型的缓冲区
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向缓冲区存放数据  capacity()获取缓冲区的容量
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }

        //读写转换. 写操作后进行读取缓冲区数据,必须先调用此方法
        intBuffer.flip();

        //读取缓存区数据   hasRemaining()判断缓冲区是否还有元素
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
