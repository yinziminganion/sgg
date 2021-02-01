package com.yinziming.part13.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStreamTest {
    @Test
    public void testFileInputStream() {
        File file = new File("temp.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[5];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                String s = new String(bytes, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopy() {
        long long1 = System.currentTimeMillis();
        copy("temp.txt", "temp3.txt");
        long long2 = System.currentTimeMillis();
        System.out.println(long2 - long1);
    }

    public void copy(String src, String dest) {
        File file1 = new File(src);
        File file2 = new File(dest);
        FileInputStream fileReader = null;
        FileOutputStream fileWriter = null;
        try {
            fileReader = new FileInputStream(file1);
            fileWriter = new FileOutputStream(file2);
            byte[] bytes = new byte[5];
            int len;
            while ((len = fileReader.read(bytes)) != -1) {
                fileWriter.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
