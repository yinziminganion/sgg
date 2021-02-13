package com.yinziming.part02.aop.annotation;

import org.springframework.stereotype.Component;

@Component
public class User {
    public void add() {
        System.out.println("User.add()");
    }
}
