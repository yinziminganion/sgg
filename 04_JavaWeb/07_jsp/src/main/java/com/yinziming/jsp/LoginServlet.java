package com.yinziming.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter writer = resp.getWriter();
        if ("admin".equals(username) && "admin".equals(password)) {
            Cookie cookie = new Cookie("username", "admin");
            Cookie cookie1 = new Cookie("password", "admin");
            cookie.setMaxAge(60 * 60 * 24 * 7);//设置一周内有效
            cookie1.setMaxAge(60 * 60 * 24 * 7);//设置一周内有效
            resp.addCookie(cookie);
            resp.addCookie(cookie1);
            writer.write("login success, directing...");
            req.getRequestDispatcher("/admin/index.html").forward(req, resp);
        } else {
            writer.write("login failed");
        }
    }
}
