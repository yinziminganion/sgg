package com.yinziming.part12.crud.entities;

public class Employee {
    private Integer id;
    private String name;
    private String email;
    private Integer gender;
    private Department department;

    public Employee(int id, String name, String email, int gender, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Employee(Integer id, Integer gender, Integer departmentId) {
        this.id = id;
        this.gender = gender;
        this.department = new Department(departmentId);
        this.name = "Employee" + id;
        this.email = this.name.toLowerCase() + "@xxx.com";
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                '}';
    }
}
