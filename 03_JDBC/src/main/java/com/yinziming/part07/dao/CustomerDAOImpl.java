package com.yinziming.part07.dao;

import com.yinziming.part03.statement.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {
    @Override
    public void insert(Connection connection, Customers c) {
        String sql = "insert into jdbc.customers(name, email,birth)values(?,?,?)";
        update(connection, sql, c.getName(), c.getEmail(), c.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from jdbc.customers where id=?";
        update(connection, sql, id);
    }

    @Override
    public void updateById(Connection connection, Customers c) {
        String sql = "update jdbc.customers set name=?, email=?, birth=? where id=?";
        update(connection, sql, c.getName(), c.getEmail(), c.getBirth(), c.getId());
    }

    @Override
    public Customers getCustomerById(Connection connection, int id) {
        String sql = "select id, name, email, birth from customers where id=?";
        List<Customers> instance = getInstance(connection, sql, id);
        return instance.get(0);
    }

    @Override
    public List<Customers> getAll(Connection connection) {
        String sql = "select id, name, email, birth from customers";
        return getInstance(connection, sql);
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(*) from customers";
        return (long) getValue(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(birth) from customers";
        return (Date) getValue(connection, sql);
    }
}
