package com.yinziming.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");
        if (null == username) {
            try {
                servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }
}