package com.yinziming.part12.crud.dao.impl;

import com.yinziming.part12.crud.dao.DepartmentDao;
import com.yinziming.part12.crud.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private static Map<Integer, Department> departments = new HashMap<>();

    static {
        departments.put(101, new Department(101));
        departments.put(102, new Department(102));
        departments.put(103, new Department(103));
        departments.put(104, new Department(104));
        departments.put(105, new Department(105));
    }


    @Override
    public Department get(int id) {
        return departments.get(id);
    }

    @Override
    public Collection<Department> getAll() {
        return departments.values();
    }

    @Override
    public void save(Department department) {
        departments.put(department.getId(), department);
    }

    @Override
    public void delete(int id) {
        departments.remove(id);
    }
}
