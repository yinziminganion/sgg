package com.yinziming.part01.ioc.xml;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("add()");
        userDao.update();
    }
}
