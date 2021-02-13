package com.yinziming.part01.ioc.annotation1;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("UserDaoImpl.add()");
    }
}
