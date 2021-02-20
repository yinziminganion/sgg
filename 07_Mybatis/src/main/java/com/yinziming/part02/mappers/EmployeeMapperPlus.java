package com.yinziming.part02.mappers;

import java.util.List;

public interface EmployeeMapperPlus {
    public Employee getById(Integer id);

    public Employee getDepartment(Integer id);

    public Employee getByIdByStep(Integer id);

    public List<Employee> getByDeptId(Integer deptId);

    public Employee getEmployByDiscriminator(Integer id);
}
