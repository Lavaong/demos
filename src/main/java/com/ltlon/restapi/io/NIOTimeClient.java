package com.ltlon.restapi.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class NIOTimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        TimeClientHandler timeClientHandler = new TimeClientHandler("127.0.0.1",port);
        new Thread(timeClientHandler,"Time Client-001").start();
    }
}
