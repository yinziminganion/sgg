# Filter

## 概念

是JavaWeb的三大组件之一，是一种接口

## 作用

*拦截请求*，过滤响应

拦截请求常用场景：①权限检查②日记操作③事务管理 ……

## 权限检查example

要求在web工程下，有一个admin目录，这个目录之下所有资源都必须在登录之后才能访问，

## 使用

1. 编写一个类去实现Filter接口
2. 实现过滤方法doFilter()
3. web.xml配置。在多个Filter过滤器执行的时候，执行的优先顺序是由它们在web.xml中从上到下配置的顺序决定。

多个Filter过滤器执行的特点：

1. 所有Filter和目标资源默认都执行在同一个线程中
2. 多个Filter共同执行的时候，它们都使用同一个Request对象

```java
public class AdminFiler implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");
        System.out.println("username = " + username);
        if (null == username) {
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
```

## 生命周期

1. 构造器方法
1. init初始化方法
1. doFilter过滤方法
1. destroy销毁方法

第1、2步在web工程启动的时候执行，第3步在每次拦截到请求的时候就会执行，第4步在停止web工程的时候执行

## FilterConfig类

是Filter过滤器的配置文件类，Tomcat每次创建Filter时也会创建一个FilterConfig类，包含了Filter配置文件的配置信息

1. 获取Filter的名称 filter-name的内容
2. 获取Filter中配置的init-param初始化参数
2. 获取ServletContext对象

## FilterChain过滤器链

FilterChain.doFilter()方法的作用

1. 执行下一个Filter过滤器（如果有）
2. 执行目标资源（没有Filter）

## Filter的拦截路径

### 精确匹配

```xml
<!--表示请求地址必须为http://ip:port/工程名/target.jsp-->
<url-pattern>/target.jsp</url-pattern>
```

### 目录匹配

```xml
<!--表示请求地址必须为http://ip:port/工程名/admin/*-->
<url-pattern>/admin/*</url-pattern>
```

### 后缀名匹配

```xml
<!--表示请求地址必须为以.html结尾，-->
<url-pattern>*.html</url-pattern>
        <!--<url-pattern>/*.html</url-pattern> 这种是错误的-->
```

