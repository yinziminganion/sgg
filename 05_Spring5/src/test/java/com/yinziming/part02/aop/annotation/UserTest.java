package com.yinziming.part02.aop.annotation;

import com.yinziming.part02.aop.xml.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserTest {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);//加载配置类
        User user = context.getBean("user", User.class);
        user.add();
    }
    @Test
    public void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }
}