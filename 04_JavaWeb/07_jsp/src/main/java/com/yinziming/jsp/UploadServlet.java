package com.yinziming.jsp;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(name = "helloServlet", value = "/hello")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        resp.setContentType("text/html; charset=utf-8");
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = null;
            try {
                list = servletFileUpload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            assert list != null;
            for (FileItem item : list) {
                if (item.isFormField()) {
                    System.out.println("item.getFieldName() = " + item.getFieldName());
                    try {
                        System.out.println("item.getString(\"gbk\") = " + item.getString("gbk"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("item.getFieldName() = " + item.getFieldName());
                    System.out.println("item.getName() = " + item.getName());
                    try {
                        item.write(new File("D:\\temp\\" + item.getName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}