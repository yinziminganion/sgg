package com.yinziming.part02.mappers;

public interface DepartmentMapper {
    public Department getById(Integer id);

    public Department getByIdPlus(Integer id);

    public Department getByIdStep(Integer id);
}
