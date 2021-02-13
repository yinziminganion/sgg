package com.yinziming.part01.ioc.xml.autowire;

public class Employee {
    private Department department;
    private String name;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department=" + department +
                ", name='" + name + '\'' +
                '}';
    }
    public void test(){
        System.out.println("Employee.department = " + this.department);
    }
}

