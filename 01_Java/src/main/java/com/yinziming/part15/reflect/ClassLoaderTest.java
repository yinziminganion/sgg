package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    @Test
    public void test1() {//jdk11
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader1);//jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc
        //调用系统类加载器的getParent可以获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);//jdk.internal.loader.ClassLoaders$PlatformClassLoader@654f0d9c
        //调用扩展类加载器的getParent无法获取引导类加载器，引导类加载器主要负责加载java核心类库
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);//获取不到，为null
        System.out.println(String.class.getClassLoader());//也获取不到，为null，因为String属于核心类库
    }

    /**
     * 使用ClassLoader加载配置文件
     */
    @Test
    public void test2() {
        Properties p = new Properties();
//        老方法  配置文件在当前module根目录下
//        try (FileInputStream fis = new FileInputStream("test.properties")) {
//            p.load(fis);
//            String name = p.getProperty("name");
//            String password = p.getProperty("password");
//            System.out.println("name = " + name + ", password = " + password);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //ClassLoader  配置文件在当前模块src文件夹下
        //（但是我自己在idea尝试的时候发现当前工程下和当前模块src下必须都有该文件才行？）
        //后来发现模块src下的test.properties内容和模块根目录下的test.properties不一样也可，读出来的是src下文件的内容
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        try {
            InputStream is = classLoader.getResourceAsStream("test.properties");
            p.load(is);
            String name = p.getProperty("name");
            String password = p.getProperty("password");
            System.out.println("name = " + name + ", password = " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
