package com.yinziming.part07.dao;

import com.yinziming.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对于数据表的通用的操作
 */
public abstract class BaseDAO<T> {
    private Class<T> clazz = null;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    public void update(Connection connection, String sql, Object... args) {//通用增删改操作v2.0(考虑事务)
        PreparedStatement ps = null;
        try {
//            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(ps);
        }
    }

    public List<T> getInstance(Connection connection, String sql, Object... args) {
        PreparedStatement ps = null;
        ArrayList<T> ts = new ArrayList<>();
        try {
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
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                ts.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(ps);
        }
        return ts;
    }

    public <E> E getValue(Connection connection, String sql, String... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        E e = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                e = (E) resultSet.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(ps, resultSet);
        }
        return e;
    }

}
