package com.yinziming.part03.jdbctemplate.service;

import com.yinziming.part03.jdbctemplate.bean.Book;
import com.yinziming.part03.jdbctemplate.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addBook(Book book) {
        bookDao.add(book);
    }

    public void update(Book book) {
        bookDao.updateBook(book);
    }

    public void delete(Book book) {
        bookDao.deleteBook(book);
    }

    public int count() {
        return bookDao.count();
    }
    public Book find(String id){
        return bookDao.find(id);
    }
    public List<Book> list(){
        return bookDao.list();
    }

    public void add(List<Object[]> batchArgs){
        bookDao.add(batchArgs);
    }
}
