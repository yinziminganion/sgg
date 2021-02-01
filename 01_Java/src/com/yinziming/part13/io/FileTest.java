package com.yinziming.part13.io;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileTest {
    @Test
    public void test1() {
        File file = new File("test.properties");
        System.out.println(file.getAbsolutePath());

        File file1 = new File("a", "b");
        System.out.println(file1);

        File file2 = new File(file1, "c.txt");
        System.out.println(file2);
    }

    @Test
    /*
     *  String getAbsolutePath()获取绝对路径
     *  String getPath()获取路径
     *  String GetName()获取名称
     *  String getParent()获取上层文件目录路径，若不存在返回null
     *  long Length()获取文件字节数，不能获取目录的
     *  lastModified()获取最后一次的修改时间，毫秒
     *
     *  String[] list()获取指定目录下的所有文件或者文件目录的名称数组
     *  File[] listFiles()获取指定目录下的所有文件或者文件目录的File数组
     *
     *  boolean isDirectory()是否是目录
     *  boolean isFile()是否是文件
     *  boolean exists()是否存在
     *  boolean canRead()是否可读
     *  boolean canWrite()是否可写
     *  boolean isHidden()是否隐藏
     *
     *  boolean createNewFile()创建文件，如果文件存在则不创建并返回false
     *  boolean mkdir()创建目录，如果已存在就不创建，或者此文件目录的上级目录不存在也不创建
     *  boolean mkdirs()创建目录，如果上层目录不存在则一并创建
     *
     *  boolean delete()删除文件或文件夹，不会收到回收站而是直接删除，需要谨慎使用
     */
    public void test2() {
    }
}
