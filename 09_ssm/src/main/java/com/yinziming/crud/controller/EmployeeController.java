package com.yinziming.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinziming.crud.bean.Employee;
import com.yinziming.crud.bean.Msg;
import com.yinziming.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //    @RequestMapping("/employee")
    public String getEmployees(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 10);
        List<Employee> list = employeeService.getAll();//startPage()后面紧跟的这个查询就是一个分页查询
        PageInfo pageInfo = new PageInfo(list, 5);//使用PageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

    @ResponseBody
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Msg getEmployeesWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 10);
        List<Employee> list = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(list, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public Msg save(Employee employee) {
        employeeService.save(employee);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    public Msg checkUser(String empName) {
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regex)) {
            return Msg.fail().add("va_msg", "用户名必须是6到16位数字和字母的组合或2到5位中文");
        }
        if (employeeService.checkUser(empName)) {
            return Msg.success();
        } else {
            return Msg.fail().add("Va_msg", "用户名已存在");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Msg getEmployee(@PathVariable("id") Integer id) {
        Employee e = employeeService.getById(id);
        return Msg.success().add("employee", e);
    }

    @ResponseBody
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.PUT)
    public Msg updateEmployee(Employee e) {
        System.out.println("e = " + e);
        employeeService.updateOne(e);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public Msg deleteById(@PathVariable("id") String id) {
        if (id.contains("-")) {
            String[] strings = id.split("-");
            for (String s : strings) {
                employeeService.deleteById(Integer.parseInt(s));
            }
        } else {
            employeeService.deleteById(Integer.parseInt(id));
        }
        return Msg.success();
    }
}
