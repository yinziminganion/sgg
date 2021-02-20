package com.yinziming.crud.controller;

import com.github.pagehelper.PageInfo;
import com.yinziming.crud.bean.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:D:\\Codes\\IDEA\\sgg\\09_ssm\\src\\main\\webapp\\WEB-INF\\dispatcherServlet-servlet.xml"})
@WebAppConfiguration
class EmployeeControllerTest {
    @Autowired
    private WebApplicationContext context;
    MockMvc mockMvc;

    @BeforeEach
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getEmployees() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employee").param("pn", "5")).andReturn();
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码pageInfo.getPageNum() = " + pageInfo.getPageNum());
        System.out.println("总页码pageInfo.getPages() = " + pageInfo.getPages());
        System.out.println("总记录数pageInfo.getTotal() = " + pageInfo.getTotal());
        System.out.println("在页面连续显示的页码pageInfo.getNavigatePageNums() = " + Arrays.toString(pageInfo.getNavigatepageNums()));
        List<Employee> list = pageInfo.getList();
        list.forEach(employee -> {
            System.out.println("employee.getEmpId() = " + employee.getEmpId() + ", employee.getEmpName() = " + employee.getEmpName());
        });
    }

}