package com.yinziming.part13.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccessFileTest {
    @Test
    public void test1() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("temp.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("temp.txt", "rw");
            file.seek(3);
            file.write("public static void main(){}".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
