package com.yinziming.part01.ioc;

import com.yinziming.part01.ioc.annotation1.SpringConfig;
import com.yinziming.part01.ioc.xml.Employee;
import com.yinziming.part01.ioc.xml.Orders;
import com.yinziming.part01.ioc.xml.Student;
import com.yinziming.part01.ioc.xml.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

public class BeanTest {
    @Test
    public void test1() {//外部Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("user.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    @Test
    public void test2() {//内部Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        Employee emp = context.getBean("emp", Employee.class);
        emp.add();
    }

    @Test
    public void test3() {//集合
        ApplicationContext context = new ClassPathXmlApplicationContext("collection.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println("stu.getCourses() = " + Arrays.toString(stu.getCourses()));
        System.out.println("stu.getList() = " + stu.getList());
        System.out.println("stu.getMap() = " + stu.getMap());
        System.out.println("stu.getSet() = " + stu.getSet());
    }

    @Test
    public void test4() {//工厂Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("factory.xml");
        Student myBean = context.getBean("myBean", Student.class);
        System.out.println("myBean = " + myBean);
    }

    @Test
    public void test5() {//生命周期
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("orders.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("6. orders = " + orders);
        context.close();
    }

    @Test
    public void test6() {//自动装配
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autowire.xml");
        com.yinziming.part01.ioc.xml.autowire.Employee emp = context.getBean("emp", com.yinziming.part01.ioc.xml.autowire.Employee.class);
        System.out.println("emp = " + emp);
    }

    @Test
    public void test7() {//引入外部properties文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("druid.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        System.out.println("dataSource = " + dataSource);
    }

    @Test
    public void testAnnotation1() {//基于注解创建对象属性注入
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotation.xml");
        com.yinziming.part01.ioc.annotation.UserService userService = context.getBean("userService", com.yinziming.part01.ioc.annotation.UserService.class);
        System.out.println("userService = " + userService);
        userService.add();
    }

    @Test
    public void testAnnotation2() {//完全注解
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);//加载配置类
        com.yinziming.part01.ioc.annotation1.UserService userService = context.getBean("userService", com.yinziming.part01.ioc.annotation1.UserService.class);
        System.out.println("userService = " + userService);
        userService.add();
    }
}
