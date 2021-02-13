package com.yinziming.part01.ioc.xml;

public class Orders {
    private String name;

    public void setName(String name) {
        System.out.println("2. setName()");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    Orders() {
        System.out.println("1. Orders()");
    }

    public void initMethod() {
        System.out.println("4. initMethod()");
    }

    public void destroyMethod(){
        System.out.println("7. destroyMethod()");
    }
}
