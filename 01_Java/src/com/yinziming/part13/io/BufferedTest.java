package com.yinziming.part13.io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class BufferedTest {
    @Test
    public void testBufferedStream() {
        File file1 = new File("temp.txt");
        File file2 = new File("temp4.txt");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[5];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
