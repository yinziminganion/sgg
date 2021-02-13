package com.yinziming.part01.ioc.xml;

public class Employee {
    private String name, gender;
    private Department department;//员工属于某个部门，使用对象形式表示

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public void add(){
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("department = " + department);
    }
}
