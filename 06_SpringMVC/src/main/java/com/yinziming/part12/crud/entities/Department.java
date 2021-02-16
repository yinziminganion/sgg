package com.yinziming.part12.crud.entities;

public class Department {
    private Integer id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
        this.name = "Department" + id;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
