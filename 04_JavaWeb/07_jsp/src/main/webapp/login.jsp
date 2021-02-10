<%--
  Created by IntelliJ IDEA.
  User: yinzi
  Date: 2021/2/10
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    username<input type="text" name="username" value="${cookie.username.value}"/><br/>
    password<input type="password" name="password" value="${cookie.password.value}"/><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
