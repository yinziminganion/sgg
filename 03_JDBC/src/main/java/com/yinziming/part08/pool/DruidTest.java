package com.yinziming.part08.pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.yinziming.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    public void getConnection() {
        InputStream is = null;
        Connection connection = null;
        try {
            Properties properties = new Properties();
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, is);
        }
    }
}
