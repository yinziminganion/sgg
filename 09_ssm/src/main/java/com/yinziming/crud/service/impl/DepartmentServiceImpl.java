package com.yinziming.crud.service.impl;

import com.yinziming.crud.bean.Department;
import com.yinziming.crud.dao.DepartmentMapper;
import com.yinziming.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectByExample(null);
    }
}
