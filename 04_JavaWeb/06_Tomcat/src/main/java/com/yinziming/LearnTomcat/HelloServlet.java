package com.yinziming.LearnTomcat;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("1 constructor");
    }

    @Override
    public void init(ServletConfig servletConfig) {
        System.out.println("2 init");

        System.out.println("HelloServlet的别名是" + servletConfig.getServletName());

        System.out.println("username = " + servletConfig.getInitParameter("username"));
        System.out.println("url = " + servletConfig.getInitParameter("url"));

        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("3 service");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        System.out.println("method = " + method);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4 destroy");
    }
}