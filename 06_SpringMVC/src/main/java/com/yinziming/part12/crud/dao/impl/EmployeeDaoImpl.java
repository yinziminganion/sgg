package com.yinziming.part12.crud.dao.impl;

import com.yinziming.part12.crud.dao.DepartmentDao;
import com.yinziming.part12.crud.dao.EmployeeDao;
import com.yinziming.part12.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private DepartmentDao departmentDao;
    private static final Map<Integer, Employee> employees = new LinkedHashMap<>();
    private static int initId = 1006;

    static {
        employees.put(1001, new Employee(1001, 1, 103));
        employees.put(1002, new Employee(1002, 0, 101));
        employees.put(1003, new Employee(1003, 1, 102));
        employees.put(1004, new Employee(1004, 0, 105));
        employees.put(1005, new Employee(1005, 1, 104));
    }

    @Override
    public Employee get(int id) {
        return employees.get(id);
    }

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.get(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    @Override
    public void delete(int id) {
        employees.remove(id);
    }

}
