package com.yinziming.LearnTomcat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        String password = servletContext.getInitParameter("password");
        System.out.println("password = " + password);

        System.out.println("servletContext.getContextPath() = " + servletContext.getContextPath());

        System.out.println("servletContext.getRealPath() = " + servletContext.getRealPath("/"));

        System.out.println("servletContext.getAttribute(\"k1\") = " + servletContext.getAttribute("k1"));
        servletContext.setAttribute("k1", "v1");
        System.out.println("servletContext.getAttribute(\"k1\") = " + servletContext.getAttribute("k1"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
