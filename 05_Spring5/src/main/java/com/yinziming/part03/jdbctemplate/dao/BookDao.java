package com.yinziming.part03.jdbctemplate.dao;

import com.yinziming.part03.jdbctemplate.bean.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    void add(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    int count();

    Book find(String id);

    List<Book> list();

    void add(List<Object[]> batchArgs);
}
