package com.itcodes.biodemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName BIODemo   同步阻塞IO通信 服务器端
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/15
 */
public class BIODemo {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //创建serverSocket
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务器启动");

            while (true){
                //循环监听客户端连接
                Socket accept = serverSocket.accept();
                System.out.println("监听到一个客户端连接...");

                //开启线程池处理单个客户端的通信
                cachedThreadPool.execute(() -> {
                    //与客户端通信
                    handler(accept);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //与客户端通信
    private static void handler(Socket accept) {
        try {
            byte[] bytes = new byte[1024];
            //通过socket,获取输入流
            InputStream input = accept.getInputStream();

            //循环读取客户端发送的数据
            while (true){
                int read = input.read();
                if (read != -1){
                    //将获取到的客户端输入打印
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
