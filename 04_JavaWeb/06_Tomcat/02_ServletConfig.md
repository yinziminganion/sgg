# ServletConfig 类

ServletConfig类，从类名上来看，就知道是Servlet程序的配置信息类

Servlet程序和ServletConfig对象都是由Tomcat负责创建，我们负责使用

Servlet默认是第一次访问时创建，ServletConfig是每个Servlet程序创建时，就创建一个对应的ServletConfig对象

## ServletConfig类的三大作用

1. 可以获取Servlet程序的别名servlet-name的值
2. 获取初始化参数init-param
3. 获取ServletContext对象

