package com.yinziming.part17.java9;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TransferToTest {
    @Test
    public void test() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("test.properties")) {//当前module根目录下的文件
            FileOutputStream os = new FileOutputStream("temp.properties");
            assert is != null;
            is.transferTo(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
