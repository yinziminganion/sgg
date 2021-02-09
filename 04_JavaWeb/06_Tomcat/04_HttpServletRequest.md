# HttpServletRequest

## 作用

每次只要有请求进入tomcat服务器，tomcat服务器就会把请求过来的http协议信息解析好防撞到Request对象中，然后传递到service方法（doGet和doPost）中给我们使用。

我们可以通过HttpServletRequest对象，获取到所有请求的信息

## 常用方法

- getRequestURI()                   获取请求的资源路径
- getRequestURL()                   获取请求的统一资源定位符（绝对路径）
- getRemoteHost()                   获取客户端的ip地址
- getHeader()                       获取请求头
- getParameter()                    获取请求的参数
- getParameterValues()              获取请求的参数（多个值的时候使用）
- getMethod()                       获取请求的方式GET或POST
- setAttribute(key, value)          设置域数据
- getAttribute(key)                 获取域数据
- getRequestDispatcher()            获取请求转发对象
- setCharacterEncoding("UTF-8")     设置请求体的字符串为UTF-8，只能在调用getParameter之前设置

## 请求的转发

服务器收到请求后，从一个资源跳转到另一个资源的操作

```java
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        req.setAttribute("key", "柜台1的章");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/servlet2");
        dispatcher.forward(req, resp);
    }
}
```

```java
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        Object key = req.getAttribute("key");
        System.out.println("Servlet1的章" + key);

        System.out.println("Servlet2处理业务");
    }
}
```

## base标签的作用

```html

<html lang="zh">
<head>
<!-- base标签的作用：设置页面相对路径工作时参照的地址 -->
    <base href="http://localhost:8080/project/a/b/">
</head>
<body>
<a href="../../index.html">跳回首页</a>
</body>
</html>
```