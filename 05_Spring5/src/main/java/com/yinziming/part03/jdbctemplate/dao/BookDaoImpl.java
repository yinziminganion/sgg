package com.yinziming.part03.jdbctemplate.dao;

import com.yinziming.part03.jdbctemplate.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into book values(?,?,?)";
        int update = jdbcTemplate.update(sql, book.getId(), book.getUsername(), book.getStatus());
        System.out.println("update = " + update);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update book set username=?, status=? where id=?";
        int update = jdbcTemplate.update(sql, book.getUsername(), book.getStatus(), book.getId());
        System.out.println("update = " + update);
    }

    @Override
    public void deleteBook(Book book) {
        String sql = "delete from book where id=?";
        int update = jdbcTemplate.update(sql, book.getId());
        System.out.println("update = " + update);
    }

    @Override
    public int count() {
        String sql = "select count(*) from book";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Book find(String id) {
        String s = "select * from book where id=?";
        return jdbcTemplate.queryForObject(s, new BeanPropertyRowMapper<Book>(Book.class), id);
    }

    @Override
    public List<Book> list() {
        String s = "select * from book";
        List<Book> books = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    @Override
    public void add(List<Object[]> batchArgs) {
        String s = "insert into book values(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(s, batchArgs);
        System.out.println("ints = " + Arrays.toString(ints));
    }
}
