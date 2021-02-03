package com.yinziming.part03.statement;

import com.yinziming.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CommonPreparedStatementTest {
    public <T> List<T> getInstance(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ArrayList<T> ts = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();//获取结果集的元数据
            int count = metaData.getColumnCount();
            while (resultSet.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < count; i++) {
                    Object value = resultSet.getObject(i + 1);
                    String name = metaData.getColumnLabel(i + 1);//getColumnName获取列的名称
                    Field field = Customers.class.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                ts.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
        return ts;
    }

    @Test
    public void testGetInstance() {
        String sql = "select id, name, email, birth from customers where id<?";
        List<Customers> customers = getInstance(Customers.class, sql, 8);
        customers.forEach(System.out::println);
    }
}
