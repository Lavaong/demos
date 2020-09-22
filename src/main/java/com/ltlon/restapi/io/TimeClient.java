package com.ltlon.restapi.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("send order 2 server succeed.");
            String res = in.readLine();
            System.out.println("now is :"+res);
        }catch (Exception e){
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e2) {

                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                }catch (Exception e4){
                    e4.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
