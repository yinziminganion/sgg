package com.yinziming.part03.statement;

import com.yinziming.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class BlobTest {
    @Test
    public void testInsert() {//如果文件大了需要修改mysql的my.ini里的max_allowed_packet=16M并重启mysql
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into jdbc.customers(name, email, birth, photo)values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, "name");
            ps.setObject(2, "email");
            ps.setObject(3, new Date(161616161616L));
            InputStream is = BlobTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            ps.setBlob(4, is);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps);
        }
    }

    @Test
    public void testSelect() {
        Connection connection = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth, photo from jdbc.customers where id=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 16);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Customers customers = new Customers(id, name, email, birth);
                System.out.println(customers);

                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("zhuyin.jpg");
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(connection, ps, fos, is);

        }
    }
}
