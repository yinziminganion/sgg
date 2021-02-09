<%--
  Created by IntelliJ IDEA.
  User: yinzi
  Date: 2021/2/9
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>9x9</title>
    <style type="text/css">
        h1{
            text-align: center;
        }
        table{
            margin: 0 auto;
            text-align: center;
            /*width: 700px;*/
        }
    </style>
</head>
<body>
<h1>九九乘法口诀表</h1>
<table border="1">
    <% for (int i = 1; i <= 9; i++) { %>
    <tr>
        <% for (int j = 1; j <= i; j++) {%>
        <td><%=j + " x " + i + " = " + (i * j)%>
        </td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
