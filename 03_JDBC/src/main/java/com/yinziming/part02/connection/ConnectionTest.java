package com.yinziming.part02.connection;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    @Test
    public void testConnection1() throws SQLException, IOException {
        Driver driver = new Driver();//com.mysql.cj.jdbc.Driver;
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "mysql");
//        info.load(ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void testConnection2() throws Exception {
        Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();//通过反射
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "mysql");
//        info.load(ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void testConnection3() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String password = "mysql";
        Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        DriverManager.registerDriver(driver);//通过DriverManager
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection4() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String password = "mysql";
        Class.forName("com.mysql.cj.jdbc.Driver");//mysql connector源码实现里已经注册了，不需要再在DriverManager注册
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection5() throws Exception {
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);//读取配置文件，数据和代码分离（解耦）
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
