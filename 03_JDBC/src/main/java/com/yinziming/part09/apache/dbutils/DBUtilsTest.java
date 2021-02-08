package com.yinziming.part09.apache.dbutils;

import com.yinziming.part03.statement.Customers;
import com.yinziming.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtilsTest {
    @Test
    public void testQueryRunner() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getConnection();
            String sql = "insert into jdbc.customers(name,email,birth) values(?,?,?)";
            int update = queryRunner.update(connection, sql, "DBUtils", "mail@apache.org", "1998-01-01");
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection);
        }

    }

    @Test
    public void testSelect1() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection();
            String sql = "select id, name from jdbc.customers where id<?";
            BeanListHandler<Customers> handler = new BeanListHandler<Customers>(Customers.class);
            List<Customers> customers = runner.query(connection, sql, handler, 10);
            customers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection);
        }
    }

    @Test
    public void testMapHandler() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection();
            String sql = "select id, name, email from jdbc.customers where id=?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(connection, sql, handler, 8);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection);
        }
    }

    @Test
    public void testScalarHandler() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection();
            String sql = "select count(*) from jdbc.customers";
            ScalarHandler<Long> handler = new ScalarHandler<>();
            Long l = runner.query(connection, sql, handler);
            System.out.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection);
        }
    }

    @Test
    public void testMyHandler() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = JDBCUtils.getConnection();
            String sql = "select count(*) from jdbc.customers";
            ResultSetHandler<Customers> handler = new ResultSetHandler<>() {
                @Override
                public Customers handle(ResultSet resultSet) throws SQLException {
                    return new Customers(30, "MyHandler", "unknown email", new Date(161616161616L));
                }
            };
            Customers customers = runner.query(connection, sql, handler);
            System.out.println(customers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection);
        }
    }
}
