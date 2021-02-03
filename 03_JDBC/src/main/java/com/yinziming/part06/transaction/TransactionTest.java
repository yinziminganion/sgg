package com.yinziming.part06.transaction;

import com.yinziming.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TransactionTest {
    /**
     * 事务：
     * 一组逻辑操作单元，使数据从一种状态变换到另一种状态，有一个或多个DML操作
     * <p>
     * 事务处理应当遵循原则是，保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。
     * 当在一个事务中执行多个操作时，要么所有事务都被提交，那么这些修改就被永久保存
     * 要么数据库管理系统将放弃所有修改，整个事务回滚到最初状态
     * <p>
     * 一旦提交就不可回滚
     * <p>
     * 会自动提交的操作：
     * DDL操作一旦执行都会自动提交
     * DML操作默认情况下一旦执行就会自动提交，可以通过set autocommit = false的方式取消DML操作的自动提交
     * 在关闭连接时默认会自动提交
     * <p>
     * 事务的ACID属性
     * 原子性：事务是一个不可分割的工作单位，事务中的操作要么都发生要么都不发生
     * 一致性：事务必须使数据库从一个一致性状态变换到另一个一致性状态
     * 隔离性：一个事务的执行不能被其他事务干扰，也即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰
     * 持久性：一个事务一旦被提交，它对数据库中的数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响
     * <p>
     * 数据库的四种隔离级别
     * READ UNCOMMITTED读未提交：允许事务读取未被其他事务提交的变更，存在脏读、不可重复读和幻读的问题
     * READ COMMITTED读已提交，只允许事务读取已经被其他事务提交的变更，可以避免脏读，存在不可重复读、幻读的问题
     * REPEATABLE READ可重复读，确保事务可以多次从一个字段中读取相同的值，在这个事务持续期间，禁止其他事务对这个字段进行更新，存在幻读的问题，MySQL默认级别
     * SERIALIZABLE串行化，确保事务可以从一个表中读取相同的行，在这个事务持续期间，禁止其他事务对该表执行插入、更新、删除，避免并发的问题，但效率差
     */
    @Test
    public void test() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            int transactionIsolation = connection.getTransactionIsolation();
            System.out.println(transactionIsolation);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            int transactionIsolation1 = connection.getTransactionIsolation();
            System.out.println(transactionIsolation1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection);
        }
    }

    public void commonUpdate(String sql, Object... args) {//通用增删改操作v1.0
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
            JDBCUtils.closeResources(ps, connection);
        }
    }

    @Test
    public void testUpdate1() {
        String sql1 = "update jdbc.user_table set balance = balance - 100 where user=?";
        String sql2 = "update jdbc.user_table set balance = balance + 100 where user=?";
        commonUpdate(sql1, "AA");
        commonUpdate(sql2, "BB");
        System.out.println("success");
    }

    public void transactionUpdate(Connection connection, String sql, Object... args) {//通用增删改操作v2.0(考虑事务)
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

    @Test
    public void testTransactionUpdate() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String sql1 = "update jdbc.user_table set balance = balance - 100 where user=?";
            String sql2 = "update jdbc.user_table set balance = balance + 100 where user=?";
            transactionUpdate(connection, sql1, "AA");
//            System.out.println(10/0);
            transactionUpdate(connection, sql2, "BB");
            connection.commit();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("rollback");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            JDBCUtils.closeResources(connection);
        }
    }
}
