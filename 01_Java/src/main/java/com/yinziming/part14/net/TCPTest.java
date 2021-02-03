package com.yinziming.part14.net;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPTest {
    @Test
    public void server() {//先启动server再启动client
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            ss = new ServerSocket(65500);
            socket = ss.accept();
            is = socket.getInputStream();

            baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[5];
            int len;
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, -0, len);
            }
            System.out.println("receive message \"" + baos.toString() + "\" from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void client() {
        OutputStream os = null;
        Socket socket = null;
        try {
            InetAddress name = InetAddress.getByName("127.0.0.1");
            socket = new Socket(name, 65500);
            os = socket.getOutputStream();
            os.write("client hello".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
