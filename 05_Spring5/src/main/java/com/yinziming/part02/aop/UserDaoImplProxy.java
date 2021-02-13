package com.yinziming.part02.aop;

import java.lang.reflect.Proxy;

public class UserDaoImplProxy {
    public static void main(String[] s) {
        UserDao userDao = new UserDaoImpl();
        UserDao instance = (UserDao) Proxy.newProxyInstance(UserDaoImplProxy.class.getClassLoader(), new Class[]{UserDao.class}, (proxy, method, args) -> {
            System.out.println("before " + method.getName());
            Object o = method.invoke(userDao, args);
            System.out.println("after  " + method.getName());
            return o;
        });
        instance.add(1, 2);
        instance.update("id");
    }
}
