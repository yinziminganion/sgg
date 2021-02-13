package com.yinziming.part00.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserTest {

    @org.junit.jupiter.api.Test
    void testAdd() {
        //1. 加载Spring配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        //2. 获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println("user = " + user);
        user.add();
    }
}