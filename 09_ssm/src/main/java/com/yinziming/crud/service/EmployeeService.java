package com.yinziming.crud.service;

import com.yinziming.crud.bean.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    void save(Employee employee);

    boolean checkUser(String empName);

    Employee getById(Integer id);

    void updateOne(Employee employee);

    void deleteById(Integer id);
}
