package com.yinziming.part07.dao;

import com.yinziming.part03.statement.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 此接口用来规范针对customer表的常用操作
 */
public interface CustomerDAO {
    void insert(Connection connection, Customers c);

    void deleteById(Connection connection, int id);

    void updateById(Connection connection, Customers c);

    Customers getCustomerById(Connection connection, int id);

    List<Customers> getAll(Connection connection);

    long getCount(Connection connection);

    Date getMaxBirth(Connection connection);
}
