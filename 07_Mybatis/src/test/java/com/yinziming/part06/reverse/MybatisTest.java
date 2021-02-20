package com.yinziming.part06.reverse;

import com.yinziming.part06.reverse.dao.EmployeeMapper;
import com.yinziming.part06.reverse.model.Employee;
import com.yinziming.part06.reverse.model.EmployeeExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

class MybatisTest {
    @Test
    public void test() throws Throwable {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(Resources.getResourceAsStream("mbg.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void testSelectOne() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectByPrimaryKey(3);
        System.out.println("employee = " + employee);
        sqlSession.close();
    }

    @Test
    public void testSelectAll1() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.selectByExample(null);
        employees.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAll2() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        EmployeeExample example = new EmployeeExample();
        example.createCriteria()
                .andLastnameLike("%e%")
                .andGenderEqualTo("1");
        List<Employee> employees = mapper.selectByExample(example);
        employees.forEach(e -> System.out.println("e.getLastname() = " + e.getLastname() + ", e.getGender() = " + e.getGender()));
        sqlSession.close();
    }

    @Test
    public void testSelectAll3() throws Throwable {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        EmployeeExample example = new EmployeeExample();
        example.createCriteria()
                .andLastnameLike("%e%")
                .andGenderEqualTo("1");
        example.or(example.createCriteria().andLastnameLike("%a%"));
        List<Employee> employees = mapper.selectByExample(example);
        employees.forEach(e -> System.out.println("e.getLastname() = " + e.getLastname() + ", e.getGender() = " + e.getGender()));
        sqlSession.close();
    }
}