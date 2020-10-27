package com.itcodes.zerocopy.oldmethod;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName OldIOClient
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/20
 */
public class OldIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7001);
        String fileName = "c:\\protoc-3.6.1-win32.zip";
        FileInputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCont;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCont = inputStream.read(buffer)) >= 0) {
            total += readCont;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节数:" + total + ",耗时:" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
