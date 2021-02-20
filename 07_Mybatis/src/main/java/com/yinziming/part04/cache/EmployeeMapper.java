package com.yinziming.part04.cache;

import org.apache.ibatis.annotations.MapKey;

import java.util.Collection;
import java.util.Map;

/**
 * 增删改可以返回的类型：Integer、Long、Boolean、void
 */
public interface EmployeeMapper {
    public Employee getById(Integer id);

    public void add(Employee employee);

    public Long update(Employee employee);

    public void deleteById(Integer id);

    public Collection<Employee> getAll();

    public Map<String, Object> getMap(Integer id);

    @MapKey("id")//告诉mybatis封装这个map的时候使用哪个属性作为主键
    public Map<Integer, Employee> getMapAll();
}
