# Servlet 技术

## 什么是Servlet

- Servlet是Java EE规范之一，规范就是接口
- Servlet是Java Web三大组件之一，三大组件分别是Servlet程序、Filter过滤器、Listener监听器
- Servlet是运行在服务器上的一个Java小程序，它可以接收客户端发送过来的请求，并响应数据给客户端

## 手动实现Servlet程序

- 编写一个类去实现Servlet接口
- 实现Service方法，处理请求，并响应数据
- 到web.xml去配置servlet程序的访问地址

## url地址如何定位到Servlet程序去访问

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.yinziming.LearnTomcat.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

## Servlet的生命周期

1. 执行Servlet构造器方法
2. 执行init初始化方法
2. 执行service方法
2. 执行destroy销毁方法

第1、2步是在第一次访问的时候会被调用 第3步是每次访问的时候都会调用 第4步在web工程停止的时候被调用

## GET和POST请求的分发处理

```java
public class HelloServlet implements Servlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        System.out.println("method = " + method);
    }
}
```

## 通过继承HttpServlet实现Servlet程序

```java
public class HelloServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HttpServlet doGet()");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HttpServlet doPost()");
    }
}
```
## Servlet继承体系
interface Servlet <- class GenericServlet <- class HttpServlet <- 自定义的Servlet程序

