package com.yinziming.part04.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("druid2.xml");
    UserService userService = context.getBean("userService", UserService.class);

    @Test
    public void testTrans() {
        userService.trans();
    }
}