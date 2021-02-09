# jsp

## 什么是jsp

jsp的全称是java server pages，java的服务器页面

## jsp的作用

jsp的主要作用是代替servlet程序回传html页面的数据

因为servlet程序回传html页面数据是一件非常繁琐的事情，开发成本和维护成本都极高

## jsp的访问

jsp页面和html页面一样，都是存放在web目录下，访问也和访问html页面一样

## jsp的本质

本质上是一个servlet程序

当我们第一次访问jsp页面的时候，tomcat服务器会帮我们把jsp页面翻译成为一个java源文件，并把它编译成为.class字节码程序

根据反编译出来的代码可以发现，jsp文件会生成一个类继承了HttpJspBase类，它直接继承了HttpServlet类。

也就是说，jsp翻译出来的java类，间接地继承了HttpServlet类。其底层实现也是通过输出流把html页面数据回传给客户端

总结：通过翻译的java代码我们可以得到结果：jsp就是Servlet程序

## jsp的三种语法

### jsp头部的page指令

jsp的page指令可以修改jsp页面中的一些重要的属性，或者行为

```html
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
```

- language 表示jsp翻译后是什么语言文件，暂时只支持java
- contentType 表示jsp返回的数据类型是什么，也是源码中的response.setContentType()
- pageEncoding 表示当前jsp文件本身的字符集
- import 跟java源代码一样，用于导包、导类
- autoFlush 设置当out输出流缓冲区满了之后，是否自动刷新，默认true
- buffer 设置out缓冲区得到小，默认是8kb
- errorPage 设置当jsp页面运行时出错时，自动跳转去的路径
- isErrorPage 设置当前jsp页面是否是错误信息页面，默认是false，如果是true可以获取异常信息
- session 设置访问当前jsp页面，是否会创建HttpSession对象，默认是true
- extends 设置jsp翻译出来的java类默认继承谁

### jsp中的常用脚本

- 声明脚本：格式是`<%!  %>`，作用是可以给jsp翻译出来的java类定义属性和方法甚至是静态代码块、内部类等
- 表达式脚本：格式是`<%= 表达式 %>`，作用是在jsp页面上输出数据（整型、浮点型、字符串、对象），特点①所有的表达式脚本都会被翻译到生成的java代码中②表达式脚本都会被翻译成out.print()
  输出到页面上③由于表达式脚本翻译的内容都在_jspService()方法中，所以该方法中的所有对象都可以直接使用④表达式脚本中的表达式不能分号结束
- 代码脚本：格式是`<% %>`，作用是在jsp页面中，编写我们自己需要的功能（写的是Java）语句，特点：①翻译之后都在_jspService()方法中②由于翻译到_jspService()
  中，所以其中现有对象都可以直接使用③还可以由多个代码脚本块组合完成一个完整的java语句④代码脚本还可以和表达式脚本一起组合使用，在jsp页面上输出数据

## jsp中的三种注释

- html注释        `<!-- -->`
- java注释        `//` `/* */`
- jsp注释         `<%-- --%>`

## jsp九大内置对象

jsp中的内置对象，是指tomcat在翻译jsp页面成为Servlet源代码后，内部提供的九个对象

```java
public final class index_jsp extends HttpJspBase implements JspSourceDependent, JspSourceImports {
    public void _jspService(final HttpServletRequest request, final HttpServletResponse response) {
        final javax.servlet.jsp.PageContext pageContext;
        javax.servlet.http.HttpSession session = null;
        final javax.servlet.ServletContext application;
        final javax.servlet.ServletConfig config;
        javax.servlet.jsp.JspWriter out = null;
        final java.lang.Object page = this;
    }
}

```

- request 请求对象
- response 响应对象
- pageContext jsp的上下文对象
- session 会话对象
- application ServletContext对象
- config ServletConfig对象
- out jsp输出流对象
- page 指向当前jsp的对象
- exception 异常对象

## jsp四大域对象

| 变量 | 对应类 | 有效范围 |
| ---- | ---- | ---- |
| pageContext | PageContextImpl类 | 当前jsp范围内有效 | 
| request | HttpServletRequest类 | 一次请求内有效 |
| session | HttpSession类 | 一次绘画范围内有效（打开浏览器访问服务器，直到关闭浏览器） | 
| application | ServletContext类 | 整个web工程范围内都有效（只要web工程不停止，数据都在）|

域对象是可以像Map一样存取数据的对象，四个域对象功能一样，不同的是它们对数据的存取范围

## jsp中的out输出和response.getWriter输出的区别

response中表示响应，我们经常用于设置返回给客户端的内容（输出）

out也是给用户做输出使用

当jsp页面中所有代码执行完成后会做以下两个操作：

1. 执行out.flush()操作，会把out缓冲区中的数据追加写入到response缓冲区末尾
2. 会执行response的刷新操作，把全部数据写给客户端

由于jsp翻译之后，底层源代码都用out来进行输出，所以一般情况下，为了避免打乱页面，我们在jsp页面中统一使用out.print()来输出

## jsp常用标签

### jsp静态包含

```html
<%@ include file="/include/file.jsp" %>
```

静态包含是把被包含的文件复制到包含的位置

### jsp动态包含

```html

<jsp:include page="/include/file.jsp"></jsp:include>
```

动态包含底层是通过调用JspRuntimeLibrary.include(request, response, "/include/file.jsp", out, false);

动态包含还可以传递参数：

```html
<!--主文件内-->
<jsp:include page="/include/file.jsp">
    <jsp:param name="username" value="user"/>
</jsp:include>
```

```html
<!--被动态包含的文件内-->
<%=request.getParameter("username")%>
```

### jsp标签-转发

```html
<!--page属性设置请求转发的路径-->
<jsp:forward page="/page.jsp"></jsp:forward>
```

## Listener监听器

Listener监听器是javaweb三大组件之一，是javaee的规范，就是接口。监听器的作用是监听某种事物的变化，然后通过回调函数，反馈给程序去做一些处理

### ServletContextListener

可以监听ServletContext对象的创建和销毁。ServletContext对象在web工程启动的时候创建，在web工程停止的时候被销毁。

监听到创建和销毁之后都会分别调用ServletContextListener监听器的方法反馈。两个方法分别是：

```java
public interface ServletContextListener extends EventListener {
    //在ServletContext对象创建之后马上调用，做初始化
    public void contextInitialized(ServletContextEvent sce);
    //在ServletContext对象销毁之后调用
    public void contextDestroyed(ServletContextEvent sce);
}
```
如何使用ServletContextListener监听器监听ServletContext对象：
1. 编写一个类去实现ServletContextListener
2. 实现其两个回调方法
3. 到web.xml中去配置监听器
```xml
<listener>
  <listener-class>xxx.xxx.xxx.MyServletContextListenerImpl</listener-class>
</listener>
```