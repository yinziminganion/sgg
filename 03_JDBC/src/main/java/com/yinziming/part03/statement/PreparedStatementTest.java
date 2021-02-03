package com.yinziming.part03.statement;

import com.yinziming.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 使用PreparedStatement替换Statement
 * 1.获取数据库的连接
 * 2.预编译sql语句，返回PreparedStatement的实例
 * 3.填充占位符
 * 4.执行
 * 5.关闭资源
 * <p>
 * ORM编程思想
 * 1.一个数据表对应一个java类
 * 2.表中的一条记录对应java类的一个对象
 * 3.表中的一个字段对应java类的一个属性
 */
public class PreparedStatementTest {

    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into jdbc.customers(name, email, birth) values(?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "名字");
            ps.setString(2, "a@a.a");
            ps.setDate(3, new Date(1612369723L));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
    }

    @Test
    public void testUpdate() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update jdbc.customers set name=? where id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "莫扎特");
            ps.setInt(2, 18);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }

    }

    public void commonUpdate(String sql, Object... args) {//通用增删改操作
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
    }

    @Test
    public void testCommonUpdate() {
        String sql = "update `order` set order_name=? where order_id=?";
        commonUpdate(sql, "DD", 2);
    }


    @Test
    public void testSelect() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from jdbc.customers where id=?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, 1);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {//boolean ResultSet.next()返回下一条是否有数据
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                Customers customers = new Customers(id, name, email, birth);
                System.out.println(customers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
    }

    /**
     * 针对customers表的通用查询操作
     */
    public Collection<Customers> commonSelect(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ArrayList<Customers> customers = new ArrayList<Customers>();
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
                Customers customer = new Customers();
                for (int i = 0; i < count; i++) {
                    Object value = resultSet.getObject(i + 1);
                    String name = metaData.getColumnName(i + 1);//getColumnName获取列的名称
                    Field field = Customers.class.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(customer, value);
                }
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
        return customers;
    }

    @Test
    public void testCommonSelect() {
        String sql = "select id, name, email, birth from customers where id<?";
        Collection<Customers> customers = commonSelect(sql, 5);
        for (var c : customers) {
            System.out.println(c);
        }
    }

    @Test
    public void testOrder() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select order_id orderId, order_name orderName, order_date orderDate from jdbc.`order` where order_id=?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, 1);
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            if (resultSet.next()) {
                Order order = new Order();
                for (int i = 0; i < count; i++) {
                    Object object = resultSet.getObject(i + 1);
                    String name = metaData.getColumnLabel(i + 1);//getColumnLabel获取列的别名
                    Field declaredField = Order.class.getDeclaredField(name);
                    declaredField.setAccessible(true);
                    declaredField.set(order, object);
                }
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
    }
}

class Order {
    private int orderId;
    private String orderName;
    private Date orderDate;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order(int orderId, String orderName, Date orderDate) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }

    public Order() {
    }
}