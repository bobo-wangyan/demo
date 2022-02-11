package com.example.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动");
        Socket accept = serverSocket.accept();

        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

//        // 扫描端口
//        int port = 1;
//
//            for(port  =1;port<1024;port++){
//                try {
//                      new ServerSocket(port);
//                System.out.println("端口开放" + port);
//                }catch (Exception e){
//                    System.out.println("端口被占用" + port);
//                }
//            }

    }
}
