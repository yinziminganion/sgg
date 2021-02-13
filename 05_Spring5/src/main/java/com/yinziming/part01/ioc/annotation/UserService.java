package com.yinziming.part01.ioc.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")//value可以不写
public class UserService {
    @Autowired
    private UserDao userDao;//不需要set方法

    public void add() {
        System.out.println("UserService add()");
        userDao.add();
    }
}
