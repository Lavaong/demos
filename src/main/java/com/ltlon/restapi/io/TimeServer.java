package com.ltlon.restapi.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket  = new ServerSocket(port);
            System.out.println("The TimeServer is start in port:"+port);
            Socket socket  = null;
            while (true){
                if(serverSocket != null){
                    socket = serverSocket.accept();
                    new Thread(new TimeServerHandler(socket)).start();
                }
            }
        }catch (IOException e2){
            e2.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    System.out.println("The TimeServer is closed");
                    serverSocket.close();
                }catch (IOException e3){
                    e3.printStackTrace();
                }
            }
        }
    }

}
