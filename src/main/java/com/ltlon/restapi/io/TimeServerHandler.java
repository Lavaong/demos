package com.ltlon.restapi.io;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

    private Socket socket = null;

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("the time server receive order:" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
            }
        } catch (IOException e1) {

        } finally {
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
            if (this.socket != null) {
                try {
                    this.socket.close();
                }catch (Exception e4){
                    e4.printStackTrace();
                }
                this.socket = null;
            }

        }
    }

    public TimeServerHandler() {
    }

    protected TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
