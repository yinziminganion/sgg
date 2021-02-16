package com.yinziming.part20.combine.controller;

import com.yinziming.part20.combine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
        System.out.println("UserController()");
    }

    @RequestMapping("/combine")
    public String hello() {
        System.out.println("success");
        return "hello";
    }
}
