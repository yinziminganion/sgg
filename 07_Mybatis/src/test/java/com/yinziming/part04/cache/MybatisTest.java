package com.yinziming.part04.cache;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class MybatisTest {
    @Test
    public void testFirstLevelCache() throws IOException, InterruptedException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper.getById(3);
        Thread.sleep(2000);
        Employee employee2 = mapper.getById(3);
        System.out.println("employee1 == employee2 = " + (employee1 == employee2));
        session.close();
    }

    @Test
    public void testFirstLevelCacheFail1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session1 = sqlSessionFactory.openSession();
        EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper1.getById(3);
        SqlSession session2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);
        Employee employee2 = mapper2.getById(3);
        System.out.println("employee1 == employee2 = " + (employee1 == employee2));
        session1.close();
    }

    @Test
    public void testFirstLevelCacheFail2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper.getById(3);
        Employee employee2 = mapper.getById(4);
        System.out.println("employee1 = " + employee1);
        System.out.println("employee2 = " + employee2);
        sqlSession.close();
    }

    @Test
    public void testFirstLevelCacheFail3() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper.getById(3);
        mapper.add(new Employee(null, "Fail", "fail@xxx.com", "0"));
        Employee employee2 = mapper.getById(3);
        System.out.println("employee1 = " + employee1);
        System.out.println("employee2 = " + employee2);
        sqlSession.close();
    }

    @Test
    public void testFirstLevelCacheFail4() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper.getById(3);
        sqlSession.clearCache();
        Employee employee2 = mapper.getById(3);
        System.out.println("employee1 = " + employee1);
        System.out.println("employee2 = " + employee2);
        sqlSession.close();
    }

    @Test
    public void testSecondLevelCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper1.getById(3);
        sqlSession1.close();
        Employee employee2 = mapper2.getById(3);
        sqlSession2.close();
        System.out.println("employee1 = " + employee1);
        System.out.println("employee2 = " + employee2);
    }
}