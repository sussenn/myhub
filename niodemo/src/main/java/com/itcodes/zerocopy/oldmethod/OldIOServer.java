package com.itcodes.zerocopy.oldmethod;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName OldIOServer
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/20
 */
public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                byte [] byteArray = new byte[4096];
                while (true){
                    int read = dataInputStream.read(byteArray, 0, byteArray.length);
                    if (read == -1){
                        break;
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
