package com.yinziming.jsp;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "downloadServlet", value = "/dl")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        resp.setContentType(servletContext.getMimeType("1.jpg"));
        resp.setHeader("Content-Disposition", "attachment; file=" + URLEncoder.encode("1.jpg", "UTF-8"));
        InputStream is = servletContext.getResourceAsStream("1.jpg");
        ServletOutputStream os = resp.getOutputStream();
        System.out.println("is = " + is);
        System.out.println("os = " + os);
        IOUtils.copy(is, os);
    }
}
