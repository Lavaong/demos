package com.ltlon.restapi.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {

    private String ip;

    private int port;

    private Selector selector;

    private SocketChannel clientChannel;

    private volatile boolean stop;

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (Exception e) {
                        if (selectionKey != null) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private void doConnect() throws IOException {
        if (clientChannel.connect(new InetSocketAddress(ip, port))) {
            clientChannel.register(selector, SelectionKey.OP_READ);
            doWrite(clientChannel);
        } else {
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    private void doWrite(SocketChannel clientChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        clientChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("send to server successd");
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            SocketChannel sc = (SocketChannel) selectionKey.channel();
            //连接成功 发送消息
            if (selectionKey.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(clientChannel);
                } else {
                    System.exit(1);
                }
            }
            //连接成功，接受消息
            if (selectionKey.isReadable()) {
                ByteBuffer bf = ByteBuffer.allocate(1024);
                int readBytes = sc.read(bf);
                if (readBytes > 0) {
                    bf.flip();
                    byte[] bytes = new byte[bf.remaining()];
                    bf.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("now is:" + body);
                    this.stop = true;
                } else if (readBytes < 0) {
                    selectionKey.cancel();
                    sc.close();
                }
            }
        }
    }

    public TimeClientHandler(String ip, int port) {
        this.ip = ip == null ? "127.0.0.1" : ip;
        this.port = port;
        try {
            selector = Selector.open();
            clientChannel = SocketChannel.open();
            clientChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


}
