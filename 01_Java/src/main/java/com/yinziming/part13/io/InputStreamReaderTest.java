package com.yinziming.part13.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamReaderTest {
    @Test
    public void test1() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("temp.txt");
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            char[] chars = new char[20];
            int len;
            while ((len = isr.read(chars)) != -1) {
                String s = new String(chars, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        File file1 = new File("temp.txt");
        File file2 = new File("temp5.txt");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            fis = new FileInputStream(file1);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            fos = new FileOutputStream(file2);
            osw = new OutputStreamWriter(fos, "gbk");
            char[] chars = new char[20];
            int len;
            while ((len = isr.read(chars)) != -1) {
                String s = new String(chars, 0, len);
                osw.write(chars, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
