package com.yinziming.part02.aop;

public class UserDaoImpl implements UserDao {
    @Override
    public int add(int a, int b) {
        System.out.println("public int add(int a, int b)");
        return a + b;
    }

    @Override
    public String update(String id) {
        System.out.println("public String update(String id)");
        return id;
    }
}
