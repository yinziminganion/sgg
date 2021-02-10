# Session会话

## 概念

Session就是一个接口(interface HttpSession)，用来维护一个客户端和服务器之间关联的一种技术，每个客户端都有自己的一个Session会话，Session会话中，我们经常用来保存用户登录后的信息。

## 创建和获取（id号、是否为新）

创建和获取Session的方法都是一样的

request.getSession() 第一次调用是创建Session会话，之后都是获取之前创建好的Session会话对象

isNew() 判断到底是不是刚创建出来的 true是刚创建的

每个会话都有一个身份证号，也就是id值，而且这个id值是唯一的 getId()

## Session域数据的存取

存`req.getSession().setAttribute("key1", "value1");`

取`Object key1 = req.getSession().getAttribute("key1");`

## Session生命周期，默认30min

`public void setMaxInactiveInterval(int interval)`

`public int getMaxInactiveInterval(int interval)`

值为正数的时候表示超时时长

值为负数表示永不超时

`public void invalidate()`让Session失效

## Session的底层原理

设置JSESSIONID这个cookie