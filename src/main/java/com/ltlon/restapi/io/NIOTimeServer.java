package com.ltlon.restapi.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NIOTimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        //多路复用TimeServer
        MultiPlexerTimeServer timeServer = new MultiPlexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
