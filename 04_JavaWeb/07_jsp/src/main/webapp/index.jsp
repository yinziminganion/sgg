<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>01_jsp概述</title>
</head>
<body>
<jsp:forward page="login.jsp"></jsp:forward>
<%!
    //    声明类属性
    private Integer Id;
    private String name;
    private static Map<String, String> map;
%>
<%!
    //    声明static静态代码块
    static {
        map = new HashMap<String, String>();
        map.put("key1", "value1");
    }
%>
<%!
    //声明类方法
    public void add(String s1, String s2) {
        map.put(s1, s2);
    }
%>
<%!
    //声明内部类
    public static class A {
        private Integer id = 12;
        private String abc = "abc";
    }
%>
</body>
</html>