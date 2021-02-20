package com.yinziming.crud.controller;

import com.yinziming.crud.bean.Department;
import com.yinziming.crud.bean.Msg;
import com.yinziming.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/department")
    public Msg getAll() {
        List<Department> list = departmentService.getAll();
        return Msg.success().add("departments", list);
    }
}
