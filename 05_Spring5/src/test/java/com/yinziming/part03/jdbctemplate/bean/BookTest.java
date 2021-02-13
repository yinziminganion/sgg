package com.yinziming.part03.jdbctemplate.bean;

import com.yinziming.part03.jdbctemplate.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

class BookTest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("druid.xml");
    BookService bookService = context.getBean("bookService", BookService.class);

    @Test
    public void test1() {//insert
        Book book = new Book();
        book.setId("id2");
        book.setStatus("status2");
        book.setUsername("username2");
        bookService.addBook(book);
    }

    @Test
    public void test2() {//update
        Book book = new Book();
        book.setStatus("updated status");
        book.setUsername("updated username");
        book.setId("id2");
        bookService.update(book);
    }

    @Test
    public void test3() {//delete
        Book book = new Book();
        book.setId("id2");
        bookService.delete(book);
    }

    @Test
    public void test4() {//count()
        System.out.println("bookService.count() = " + bookService.count());
    }

    @Test
    public void test5() {//find()
        Book book = bookService.find("id1");
        System.out.println("book = " + book);
    }

    @Test
    public void test6() {//list()
        List<Book> list = bookService.list();
        System.out.println("list = " + list);
    }

    @Test
    public void test7() {
        List<Object[]> list = new ArrayList<>();
        Object[] o3 = {"id3", "username3", "status3"};
        Object[] o4 = {"id4", "username4", "status4"};
        Object[] o5 = {"id5", "username5", "status5"};
        list.add(o3);
        list.add(o4);
        list.add(o5);
        bookService.add(list);
    }
}