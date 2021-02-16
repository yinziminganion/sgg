package com.yinziming.part12.crud.handler;

import com.yinziming.part12.crud.dao.DepartmentDao;
import com.yinziming.part12.crud.dao.EmployeeDao;
import com.yinziming.part12.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeHandler {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        map.put("departments", departmentDao.getAll());
        System.out.println("http://localhost:8080/SpringMVC/employee/list");
        return "/crud/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "name") String name,
                      @RequestParam(value = "email") String email,
                      @RequestParam(value = "gender") String gender,
                      @RequestParam(value = "department") String department) {
        Employee e = new Employee();
        e.setName(name);
        e.setEmail(email);
        e.setGender(gender.equals("male") ? 1 : 0);
        e.setDepartment(departmentDao.get(Integer.parseInt(department.substring(10))));
        employeeDao.save(e);
        System.out.println("http://localhost:8080/SpringMVC/employee/add\tsuccessfully added");
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") String id) {
        employeeDao.delete(Integer.parseInt(id));
        System.out.println("http://localhost:8080/SpringMVC/employee/add\tsuccessfully deleted");
        return "redirect:/employee/list";
    }
}
