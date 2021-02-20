package com.yinziming.part02.mappers;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

class EmployeeMapperTest {
    @Test
    public void testAdd() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());

        Employee employee = new Employee(null, "Jerry", "jerry@xxx.com", "1");
        System.out.println("添加到数据库前 employee.getId() = " + employee.getId());
        mapper.add(employee);
        System.out.println("添加到数据库后 employee.getId() = " + employee.getId());

        openSession.commit();//提交

        openSession.close();
        is.close();
    }

    @Test
    public void testUpdate() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());

        Long update = mapper.update(new Employee(2, "Mouse", "mouse@xxx.com", "0"));
        openSession.commit();//提交
        System.out.println("update = " + update);

        openSession.close();
        is.close();
    }

    @Test
    public void testDelete() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());

        mapper.deleteById(2);
        openSession.commit();//提交

        openSession.close();
        is.close();
    }

    @Test
    public void testGetAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Collection<Employee> all = mapper.getAll();
        all.forEach(System.out::println);
        openSession.commit();
        openSession.close();
        is.close();
    }

    @Test
    public void testGetMap() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Map<String, Object> map = mapper.getMap(3);
        System.out.println("map = " + map);
        openSession.commit();
        openSession.close();
        is.close();
    }

    @Test
    public void testGetMapAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Map<Integer, Employee> map = mapper.getMapAll();
        System.out.println("map = " + map);
        openSession.commit();
        openSession.close();
        is.close();
    }

    @Test
    public void testGetByIdByResultMap() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Employee employee = mapper.getById(3);
        System.out.println("employee = " + employee);
        openSession.close();
        is.close();
    }

    @Test
    public void testGetDepartment() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Employee employee = mapper.getDepartment(3);
        System.out.println("employee = " + employee);
        openSession.close();
        is.close();
    }

    @Test
    public void testByStep() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Employee employee = mapper.getByIdByStep(3);
        System.out.println("employee = " + employee);
        openSession.close();
        is.close();
    }

    @Test
    public void testGetByIdPlus() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        mapper.getByIdPlus(1).getEmployees().forEach(System.out::println);
        openSession.close();
        is.close();
    }

    @Test
    public void testGetByIdStep() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Department department = mapper.getByIdStep(2);
        System.out.println("department = " + department);
        department.getEmployees().forEach(System.out::println);
        openSession.close();
        is.close();
    }

    @Test
    public void testGetEmployByDiscriminator() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession openSession = sqlSessionFactory.openSession();//默认不自动提交
        EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
        System.out.println("mapper.getClass().getName() = " + mapper.getClass().getName());
        Employee employ = mapper.getEmployByDiscriminator(3);
        System.out.println("employ = " + employ);
        openSession.close();
        is.close();
    }
}