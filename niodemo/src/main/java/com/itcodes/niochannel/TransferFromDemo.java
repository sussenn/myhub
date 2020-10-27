package com.itcodes.niochannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @ClassName TransferFromDemo  从目标通道复制数据到当前通道
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/16
 */
public class TransferFromDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\susse\\Pictures\\Saved Pictures\\art.jpg");
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\susse\\Downloads\\reddit\\Asian\\a.jpg");
        //转换为通道 资源目标通道/当前通道
        FileChannel sourceCh = inputStream.getChannel();
        FileChannel destCh = outputStream.getChannel();

        //复制
        destCh.transferFrom(sourceCh,0,sourceCh.size());

        inputStream.close();
        outputStream.close();
    }
}
