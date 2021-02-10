# Cookie

## 什么是Cookie

Cookie是服务器通知客户端保存键值对的一种技术

客户端有了cookie后，每次请求都发送给服务器

每个cookie的大小不能超过4kb

## 如何创建cookie

```java
public class CookieServlet extends HttpServlet {
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("key1", "value1");
        resp.addCookie(cookie);
        resp.getWriter().write("cookie创建成功");
    }
}
```

## 服务器如何获取客户端上的cookie

`Cookie[] cookies = req.getCookies()`

## 修改cookie

和创建cookie一样，只不过创建cookie时的key与要被修改的cookie的key相同，然后通知客户端修改

或者找到要被修改的cookie，使用setValue()赋予新的值，再通知客户端修改

## Cookie生命控制

是指如何管理cookie什么时候被销毁（删除）

void setMaxAge(int expiry)

正数表示指定的秒数后过期，负数表示浏览器关闭时删除，0表示马上删除

## Cookie有效路径Path的设置

Cookie的path属性可以有效过滤哪些cookie可以发送给服务器，哪些不发。path属性是通过请求的地址来进行有效过滤

`cookie.setPath(req.getContextPath() + "/abc");// cookie的路径为/工程路径/abc`

## 应用：免输入用户名登录