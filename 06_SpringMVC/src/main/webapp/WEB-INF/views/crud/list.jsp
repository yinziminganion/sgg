<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: yinzi
  Date: 2021/2/15
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>

<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>lastName</th>
        <th>email</th>
        <th>gender</th>
        <th>department</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${requestScope.employees}" var="e">
        <tr>
            <td>${e.id}</td>
            <td>${e.name}</td>
            <td>${e.email}</td>
            <td>${e.gender == 0 ? "female" : "male"}</td>
            <td>${e.department.name}</td>
            <td><a href="">edit</a></td>
            <td><a href="${pageContext.request.contextPath}/employee/delete?id=${e.id}">delete</a></td>
        </tr>
    </c:forEach>
    <form action="add" method="post">
        <tr>
            <td></td>
            <td><input type="text" name="name"></td>
            <td><input type="text" name="email"></td>
            <td>
                <select name="gender">
                    <option value="male" name="male">male</option>
                    <option value="female" name="female">female</option>
                </select>
            </td>
            <td>
                <select name="department">
                    <c:forEach items="${requestScope.departments}" var="d">
                        <option value="${d.name}" name="${d.name}">${d.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="添加"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </form>
</table>
<br/>
</body>
</html>
