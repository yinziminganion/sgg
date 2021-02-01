package com.yinziming.part13.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 流的分类：
 * 操作数据单位：字节流、字符流
 * 数据流向：输入流，输出流
 * 流的角色：节点流、处理流
 */
public class FileReaderWriterTest {
    @Test
    public void testFileReader() {
        File file = new File("test.properties");
        try (FileReader reader = new FileReader(file)) {
            int read = reader.read();
            while (read != -1) {
                System.out.print((char) read);
                read = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileReader1() {
        File file = new File("test.properties");
        try (FileReader reader = new FileReader(file)) {
            char[] chars = new char[5];
            int len;
            while ((len = reader.read(chars)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print(chars[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileWriter() {
        File file = new File("temp.txt");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("I have a dream!\n");
            fw.write("You need one too!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileReaderFileWriter() {
        File file1 = new File("temp.txt");
        File file2 = new File("temp2.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(file1);
            fileWriter = new FileWriter(file2);
            char[] chars = new char[5];
            int len;
            while ((len = fileReader.read(chars)) != -1) {
                for (int i = 0; i < 10000; i++) {
                    fileWriter.write(chars, 0, len);
                }
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
