package com.yinziming.crud.service.impl;

import com.yinziming.crud.bean.Employee;
import com.yinziming.crud.bean.EmployeeExample;
import com.yinziming.crud.dao.EmployeeMapper;
import com.yinziming.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    @Override
    public void save(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    @Override
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        return employeeMapper.countByExample(example) == 0;
    }

    @Override
    public Employee getById(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    @Override
    public void updateOne(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }
}
