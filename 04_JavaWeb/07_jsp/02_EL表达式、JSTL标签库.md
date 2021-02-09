# EL表达式

## 概念

全称是Expression Language，表达式语言

## 作用

主要是代替jsp页面中的表达式脚本在jsp页面中进行数据的输出，因为EL表达式在输出数据的时候比jsp的表达式脚本要简洁很多

## 格式

`${表达式}`

EL表达式在输出null值的时候输出的是空串，而jsp表达式则输出null

## EL表达式搜索域数据的顺序

EL表达式主要是输出域对象中的数据，按照pageContext, request, session, application的顺序搜索

## EL运算

### 关系运算

| 关系运算符 | 说明 | 范例 | 结果 | 
| :---: | :---: | :---: | :---: |
| == 或 eq | 等于 | ${ 5 == 5 } 或 ${ 5 eq 5 } | true | 
| != 或 ne | 不等于 | ${ 5 != 5 } 或 ${ 5 ne 5 } | false | 
| < 或 lt | 小于 | ${ 3 < 5 } 或 ${ 3 lt 5 } | true | 
| > 或 gt | 大于 | ${ 3 > 5 } 或 ${ 3 gt 5 } | false | 
| <= 或 le | 小于等于 | ${ 3 <= 5 } 或 ${ 3 le 5 } | true | 
| > = 或 ge | 大于等于 | ${ 3 >= 5 } 或 ${ 3 ge 5 } | false | 

### 逻辑运算

| 逻辑运算符 | 说明 | 范例 | 结果 | 
| :---: | :---: | :---: | :---: |
| && 或 and | 与运算 | ${ 12 == 12 && 12 < 11 } 或 ${ 12 == 12 and 12 < 11 } | false |
| &#124;&#124; 或 and | 与运算 | ${ 12 == 12 && 12 < 11 } 或 ${ 12 == 12 and 12 < 11 } | true |
| ! 或 not | 取反运算 | ${ !true } 或 ${ not true } | false |

### 算术运算

| 逻辑运算符 | 说明 | 范例 |
| :---: | :---: | :---: |
| + | 加法 | ${ 12 + 18 } | 
| - | 减法 | ${ 18 - 8 } |
| * | 乘法 | ${ 18 * 8 } |
| - 或 div | 除法 | ${ 16 / 8 } 或 ${ 16 div 8 } |
| % 或 mod | 取余 | ${ 11 % 8 } 或 ${ 11 mod 8 } |

### empty运算

可以判断一个数据是否为空，如果为空则输出true

为空的情况：

- 值为null
- 空串
- 值是Object类型的数组，长度为0的时候
- list集合元素个数为0
- map集合，元素个数为0

### 三元运算

表达式1 ? 表达式2 : 表达式3

如果表达式1的值为true则返回表达式2的值，否则返回表达式3的值

### .点运算和[]中括号运算符

点运算可以输出Bean对象中某个属性的值

[]中括号运算，可以输出有序集合中某个元素的值，还可以输出map集合中key含有特殊字符的key的值${map['a.a.a']}

## EL表达式中的11个隐含对象

| 变量 | 类型 | 作用 |
| :---: | :---: | :---: |
| pageContext | PageContextImpl | 获取jsp中的九大内置对象 |
| pageScope | Map<String, Object> | 获取pageContext域中的数据 |
| requestScope | Map<String, Object> | 获取Request域中的数据 |
| sessionScope | Map<String, Object> | 获取Session域中的数据 |
| applicationScope | Map<String, Object> | 获取ServletContext域中的数据 |
| param | Map<String, String> | 获取请求参数的值 |
| paramValues | Map<String, String[]> | 获取请求参数的值，获取多个值的时候使用 |
| header | Map<String, String> | 获取请求头的信息 |
| headerValues | Map<String, String[]> | 获取请求头的信息，获取多个值的情况 |
| cookie | Map<String, Cookie> | 获取当前请求的Cookie信息 |
| initParam | Map<String, String> | 获取在web.xml中配置的<context-param>上下文参数 |

pageContext对象主要用来获取的内容：

- 协议:pageContext.request.scheme
- 服务器ip:pageContext.request.serverName
- 服务器端口:pageContext.request.serverPort
- 获取工程路径:pageContext.request.contextPath
- 获取请求方法:pageContext.request.method
- 获取客户端ip:pageContext.request.remoteHost
- 获取会话的id编号:pageContext.session.id

# JSTL标签库

## 概念

JSTL标签库，全称JSP Standard Tag Library，JSP标准标签库，是一个不断完善的开放源代码的JSP标签库

## 作用

EL表达式主要是为了替换jsp中的表达式脚本，而标签库则是为了替换代码脚本，这样使得整个jsp页面变得更加整洁

## 组成

| 功能范围 | URI | 前缀 |
| :---: | :---: | :---: |
| 核心标签库（重点） | http://java.sun.com/jsp/jstl/core | c |
| 格式化 | http://java.sun.com/jsp/jstl/fmt | fmt |
| 函数 | http://java.sun.com/jsp/jstl/functions | fn |
| 数据库（不常用） | http://java.sun.com/jsp/jstl/sql | sql |
| XML（不常用） | http://java.sun.com/jsp/jstl/xml | x |

## 引入

`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`

## 使用

1. 导入jstl相关的jar包
2. 使用taglib指令引入标签库
3. 标签里不能使用html注释，要使用jsp注释

### <c:set />

作用：往域中保存数据

```html

<c:set scope="page" var="name" value="value"/>
```

### <c:if />

作用：做if判断

```html

<c:if test="${1 == 1}">1等于1</c:if>
```

### <c:choose > <c:when > <c:otherwise >

作用：多路判断，和switch-case-default相近

- choose标签开始选择判断
- when标签表示每一种判断情况，when的父标签一定要是choose标签
- test属性表示当前这种判断情况

```html
<% request.setAttribute("height", 190); %>
<c:choose>
    <c:when test="${requestScope.height > 190}">
        小巨人
    </c:when>
    <c:when test="${requestScope.height > 180}">
        很高了
    </c:when>
    <c:when test="${requestScope.height > 170}">
        还可以
    </c:when>
    <c:otherwise>
        剩下的
    </c:otherwise>
</c:choose>
```

### <c:forEach />

作用：遍历输出使用

#### 遍历1到10并输出

- begin属性设置开始的索引
- end属性设置结束的索引
- var属性表示用来遍历的循环变量

```html

<c:forEach begin="1" end="10" var="i">
    ${ i }
</c:forEach>
```

#### 遍历Object数组

- items表示遍历的数据源
- var表示用来遍历的循环变量

```html

<c:forEach items="${requestScope.arr}" var="item">
    ${ item }
</c:forEach>
```

#### 遍历List集合，其中存放Person类，有属性：编号、用户名、密码、年龄、电话

```html

<c:forEach items="${requestScope.students}" var="stu">
    ${ stu.id }
    ${ stu.username }
    ${ stu.password }
    ${ stu.age }
    ${ stu.phone }
</c:forEach>

```

#### 遍历Map

```html

<c:forEach items="${requestScope.map}" var="entry">
    ${ entry }
</c:forEach>
```
