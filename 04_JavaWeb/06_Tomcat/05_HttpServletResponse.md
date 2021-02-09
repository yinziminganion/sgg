# HttpServletResponse

## 作用

每次请求进来，tomcat服务器都会创建一个response对象传递给servlet程序去使用。HttpServletResponse表示所有响应的信息，我们如果需要设置返回给客户端的信息，都可以通过HttpServletRespnse对象来进行设置

## 两个输出流的说明

字节流 getOutputStream()   常用于二进制数据

字符流 getWriter()         常用于字符串

两个流同时只能使用一个，使用了字节流就不能使用字符流，反之亦然

## 如何往客户端回传数据

### 字符串数据

```java
public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");//获取流之前调用，设置服务器端和浏览器端使用的字符集
        PrintWriter writer = resp.getWriter();
        writer.write("response text");
    }
}
```

## 请求重定向

请求重定向，是指客户端给服务器发请求，然后服务器告诉客户端一个新地址让客户端去访问（因为旧的地址可能已经废弃）

### 请求重定向的第一种方案

```java
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("response1");
        resp.setStatus(302);
        resp.setHeader("Location", "http://localhost:8080/project/response2");
    }
}
```

```java
public class Response2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("response2");
    }
}
```

### 请求重定向的第二种方案（推荐）

```java
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("response1");
        resp.sendRedirect("http://localhost:8080/project/response2");
    }
}
```

请求重定向的特点

- 浏览器地址栏会发生变化
- 两次请求
- 不共享Request域中的数据
- 不能访问WEB-INF中的资源
- 可以访问工程外的资源


