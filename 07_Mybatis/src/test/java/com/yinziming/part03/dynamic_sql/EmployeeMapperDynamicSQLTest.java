package com.yinziming.part03.dynamic_sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class EmployeeMapperDynamicSQLTest {
    @Test
    public void testGetByConditionIf() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        List<Employee> list = mapper.getByConditionIf(new Employee(null, "%o%", null, null));
        list.forEach(System.out::println);
    }

    @Test
    public void testGetByConditionChoose() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        List<Employee> list = mapper.getByConditionChoose(new Employee(null, null, null, null));
        list.forEach(System.out::println);
    }

    @Test
    public void testAutoUpdateEmployee() throws IOException {//实现：填null的不更新，填写非null内容的就更新
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        mapper.autoUpdateEmployee(new Employee(3, null, "tom@email.com", null));
        openSession.commit();
        openSession.close();
    }

    @Test
    public void testGetForEach() throws IOException {//实现：填null的不更新，填写非null内容的就更新
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        List<Employee> list = mapper.getForEach(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list.forEach(System.out::println);
        openSession.commit();
        openSession.close();
    }

    @Test
    public void testAddForEach() throws IOException {//实现：填null的不更新，填写非null内容的就更新
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(null, "name1", "name1@xxx.com", "0", new Department(1)));
        list.add(new Employee(null, "name2", "name2@xxx.com", "1", new Department(2)));
        list.add(new Employee(null, "name3", "name3@xxx.com", "1", new Department(3)));
        list.add(new Employee(null, "name4", "name4@xxx.com", "1", new Department(2)));
        list.add(new Employee(null, "name5", "name5@xxx.com", "0", new Department(3)));
        list.add(new Employee(null, "name6", "name6@xxx.com", "0", new Department(1)));
        mapper.addForEach(list);
        openSession.commit();
        openSession.close();
    }
}