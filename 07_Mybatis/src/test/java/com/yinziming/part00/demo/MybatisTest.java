package com.yinziming.part00.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class MybatisTest {
    /**
     * 1. 根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     * 2. 写sql映射文件，文件中配置了每一个sql，以及sql的封装规则等，并注册在全局配置文件中
     * 3. 根据全局配置文件得到SqlSessionFactory，通过其openSession方法得到SqlSession来进行增删改查，使用唯一标识来执行某个sql
     * 4. SqlSession代表和数据库的一次对话，用完需要关闭
     * 5. SqlSession和connection引用都是非线程安全的，每次使用都需要获取新的对象
     */
    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession openSession = sqlSessionFactory.openSession();
        Employee employee = openSession.selectOne("com.yinziming.part00.demo.EmployeeMapper.getById", 1);
        System.out.println("employee = " + employee);

        openSession.close();
        is.close();
    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);//配置好mapper的xml文件后，mybatis会自动创建代理对象
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Employee employee1 = mapper.getById(1);
        System.out.println("employee1 = " + employee1);
        openSession.close();
        is.close();
    }
}
