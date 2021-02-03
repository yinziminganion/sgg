package com.yinziming.part14.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) {
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL("http://frp.yinziming.com:7500");
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getPath());
            System.out.println(url.getFile());
            System.out.println(url.getQuery());

            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            is = conn.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            System.out.println(baos);

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
            if (conn != null) conn.disconnect();
        }
    }
}
