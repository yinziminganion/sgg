package com.yinziming.part03.statement;

import com.yinziming.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class BatchInsertTest {

    private final static int cnt = 5000;
    private static final Random random = new Random();

    @Test
    public void testInsert() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        for (int i = 0; i < cnt; i++) {
            String sql = "insert jdbc.random (id, balance) values(" + random.nextInt(100) + "," + random.nextDouble() + ")";
            statement.execute(sql);
        }
        connection.commit();
    }

    @Test
    public void testBatchInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("insert jdbc.random (id, balance) values(?,?)");
            for (int i = 0; i < cnt; i++) {
                ps.setObject(1, random.nextInt(100));
                ps.setObject(2, random.nextDouble());
//                ps.execute();
                ps.addBatch();
                if (i % 500 == 0 || i == cnt - 1) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(ps, connection);
        }
    }
}
