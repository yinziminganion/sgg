package com.yinziming.part03.dynamic_sql;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    public List<Employee> getByConditionIf(Employee e);//携带了哪个字段查询条件就带上这个字段的值

    public List<Employee> getByConditionTrim(Employee e);

    public List<Employee> getByConditionChoose(Employee e);

    public void autoUpdateEmployee(Employee employee);

    public List<Employee> getForEach(List<Integer> list);

    public void addForEach(List<Employee> employees);
}
