package com.yinziming.crud;

import com.yinziming.crud.bean.Employee;
import com.yinziming.crud.dao.DepartmentMapper;
import com.yinziming.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void testMapper() {
//        departmentMapper.insertSelective(new Department("开发部"));
//        departmentMapper.insertSelective(new Department("测试部"));
//        employeeMapper.insertSelective(new Employee("Jerry", "M", 1));
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 10000; i++) {
            mapper.insertSelective(new Employee(UUID.randomUUID().toString().substring(0, 6) + i, i % 2 == 0 ? "M" : "F", i % 2 == 0 ? 1 : 2));
        }
    }
}