# SpringMVC

## Hello World

com.yinziming.part00.demo.HelloWorld

## 使用@RequestMapping映射请求

&#64;RequestMapping可以修饰类和方法

- 修饰类：提供初步的请求映射信息，相对于web应用的根目录
- 修饰方法：提供进一步的细分映射信息，相对于类定义处的url，若类未标注&#64;RequestMapping，则方法处标记的url相对于web应用的根目录

### &#64;RequestMapping的参数

| 参数 | 说明 |
| :---: | :---: |
| value | 请求url |
| method | 请求方法 |
| params | 请求参数 | 
| heads | 请求头 |

### Ant风格路径

- ? : 匹配文件名中的一个字符
- &#42; : 匹配文件名中的任意字符
- &#42;&#42; : 匹配多层路径

### &#64;PathVariable映射URL绑定的占位符

带占位符的url是spring3新增的功能，有利于实现REST，通过&#64;PathVariable可以将URL中占位符参数绑定到控制器处理方法的参数中

```
@RequestMapping("delete/{id}")
public String delete(@PathVariable("id") Integer id){
    userDao.delete(id);
    return "redirect:/user/list"
}
```

### REST

REST：Representational State Transfer，资源表现层状态转化，是目前最流行的一种互联网软件架构

资源Resources：网络上的一个实体，或者说是网络上的一个具体信息，每种资源对应一个特定的URI，因此URI即为每一个资源的独一无二的识别符

表现层Representation：资源具体呈现出来的形式叫它的表现层

状态转化State Transfer：如果客户端想要操作服务器，必须通过某种手段让服务器发生状态转化，而这种转化是建立在表现层上的，所以叫表现层状态转化

### HiddenHttpMethodFilter

浏览器form表单只支持get和post，不支持delete、put，spring3开始添加了一个过滤器，可以将这些请求转化为标准的http方法

1. 配置web.xml，添加filter

```xml
<!--在web.xml中添加filter-->
<web-app>
    <filter>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        <filter-name>HiddenHttpMethodFilter</filter-name>
    </filter>
    <filter-mapping>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

2. html的form表单中添加一个type为hidden、name为_method、value为DELETE或PUT的input

```html
<!--<input type="hidden" name="_method" value="DELETE" />-->
<form action="" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
    <input type="submit" value="test delete"/>
</form>
```

3. java部分

```
@RequestMapping("/rest/{id}", method=RequestMethod.PUT)
public String testRest(@PathVariable("id") Integer id){
    System.out.println("testRest PUT");
    return "redirect:/rest/list"
}
```

## &#64;RequestParam注解

```
@RequestMapping("/test")
public String testRequestParam(@RequestParam(value="username") String username, @RequestParam(value="age") int age){
    sout("testRequestParam username = " + username + ", age = " + age);
    return "result";
```

## &#64;RequestHeader注解

```
@RequestMapping("/test")
public String testRequestHeader(@RequestHeader(value="Accept-Encoding") String encoding, @RequestHeader(value="Keep-Alive") long alive){
    sout("testRequestHeader encoding = " + encoding + ", keep-alive = " + alive);
    return "result";
```

## &#64;CookieValue注解

```
@RequestMapping("/test")
public String testCookieValue(@RequestHeader(value="JSESSIONID", required=false) String id, @RequestParam(value="age") int age){
    sout("testCookieValue JSESSIONID = " + id + ", age = " + age);
    return "result";
```

## 使用pojo对象绑定请求参数值

SpringMVC会按请求参数名和pojo属性名进行自动匹配，自动为该对象填充属性值，支持级联属性，如dept.id, dept.address.tel等

```
@RequestMapping("/test")
public String testPojo(User user){ // http://.../test?username=yzm&dept.id=1&dept.address.tel=12345678
    return "result";
}
```

## 使用Servlet原生API

- HttpServletRequest
- HttpServletResponse
- HttpSession
- java.security.Principal
- Locale
- InputStream
- OutputStream
- Reader
- Writer

```
@RequestMapping("/test")
public String testServletAPI(HttpServletRequest request, HttpServletResponse response){
    sout("testServletAPI request = " + request + ", response = " + response);
    return "result";
}
```

## 处理模型数据

### ModelAndView

控制器处理方法的返回值如果为ModelAndView，则其既包含视图信息，也包含模型数据信息，SpringMVC会把ModelAndView的model中的数据放到request域中

1. 添加模型数据
    - ModelAndView addObject(String attributeName, Object attributeValue)
    - ModelAndView addAllObject(Map<String, ?> modelMap)
2. 设置视图
    - void setView(View v)
    - void setViewName(String viewName)

```
@RequestMapping("/test")
public ModelAndView test(){
    ModelAndView modelAndView = new ModelAndView("viewName");
    modelAndView.addObject("time", new Date());//time: ${requestScope.time}
```

### Map及Model

SpringMVC在内部使用了一个org.springframework.ui.Model接口存储模型数据

- SpringMVC在调用方法之前会创建一个隐含的模型对象作为模型数据的存储容器
- 如果方法的参数为Map或Model类型，SpringMVC会将隐含模型的引用传递给这些参数。在方法体内，开发者可以通过这个对象访问到模型中的所有数据，也可以添加新的数据

```
@RequestMapping("/test")
public String testMap(Map<String, Object> map){
    map.put("names", Arrays.asList("Tom", "Jerry"));//names: ${requestScope.names}
}
```

## &#64;SessionAttributes

若希望在多个请求之间共用某个模型属性数据，可以在控制器类上标注一个&#64;SessionAttributes，SpringMVC将在模型中对应的属性暂存到HttpSession中

&#64;SessionAttributes除了可以通过属性名指定需要放到会话中的属性外，还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中

- @SessionAttributes(types=User.class)将隐含模型中所有类型为User.class的属性添加到会话中
- @SessionAttributes(value={"user1", "user2"})
- @SessionAttributes(types={User.class, Dept.class})
- @SessionAttributes(value={"user1", "user2"}, types={Dept.class})

```
@Controller
@SessionAttributes({"user"})//只能放在类上
public class Xxx {
   @RequestMapping("/test")
   public String testSessionAttributes(Map<String, Object> map){
       map.put("user", user);//user: ${sessionScope.names}
   }
}
```

## &#64;ModelAttribute

1. 有&#64;ModelAttribute注解的方法，会在每个目标方法执行之前被SpringMVC调用
2. &#64;ModelAttribute注解也可以修饰目标方法pojo类型的入参，其value属性有以下作用：
    1. SpringMVC会使用value属性值在implicitModel中查找对应对象，若存在则直接传入到目标方法的入参中
    2. SpringMVC会以value为key，pojo对象为value，存入request中

运行流程：

1. 执行&#64;ModelAttribute注解修饰的方法，从数据库中取出对象，把对象放入map中，放入map时的键需要和目标方法参数类型的第一个字母小写的字符串一致
2. SpringMVC从map中取出user对象，并把表单的请求参数赋给该user的对应属性
3. SpringMVC把上述对象传入目标方法的参数

源代码分析的流程（Spring4）：

1. 调用&#64;ModelAttribute注解修饰的方法，实际上把&#64;ModelAttribute方法中Map中的数据放在了implicitModel中
2. 解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
    1. 确定objectName属性：若传入的attrName属性值为""
       ，则objectName为类名第一个字母小写。若目标方法的pojo属性使用了&#64;ModelAttribute来修饰，则attrName为&#64;ModelAttribute的value属性值
    2. 确定target属性
        1. 在implicitModel中查找attrName对应属性值，若不存在则验证当前handler是否使用了&#64;SessionAttributes进行修饰，
           如果使用了&#64;SessionAttributes则从Session中获取attrName所对应的属性值，若没有则抛异常
        2. 若handler没有使用&#64;SessionAttributes，或没有使用value值指定的key和attrName匹配，则通过反射创建新的pojo对象
3. SpringMVC把表单的请求参数赋给WebDataBinder的target对应的属性
3. SpringMVC会把WebDataBinder的attrName和target给implicitModel，进而传到request域对象中
3. 把WebDataBinder的target作为参数传递给目标方法的参数

SpringMVC确定目标方法pojo类型参数的过程

1. 确定一个key
    - 若目标方法的pojo类型的参数没有使用&#64;ModelAttribute作为修饰，则key为pojo类名第一个字母小写的
    - 若使用了&#64;ModelAttribute修饰，则key为&#64;ModelAttribute注解的value属性值
2. 在implicitModel中查找key对应 的对象，若存在则作为入参传入
    - 若在&#64;ModelAttribute标记的方法中在map中保存过，且key和1确定的key一致，则会获取到
2. 若implicitModel中不存在key对应的对象，则检查当前的handler是否使用了&#64;SessionAttributes注解修饰，若有且其value属性值包含了key，
   则会从HttpSession中获取key所对应的value值，若存在则直接传入目标方法的参数中，若不存在则抛异常
3. 若handler没有&#64;SessionAttributes或其value值不包含key，则通过反射创建pojo类型的参数作为目标方法的参数
5. SpringMVC会把key和value保存到implicitModel中，进而保存到request中

```
public class User{
    private String username, password, email;
    pribate int age, id;
    //构造器、setter、getter等略
}
@ModelAttribute
public void getUser(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map){
    if(id != null) {
        User user = new User(1, "Xxx", "123456", "x@x.x", 23);//模拟从数据库获取的user对象
        sout("从数据库中获取了user对象" + user);
        map.put("user", user);
    }
}
@RequestMapping("/test")
public String testModelAttribute(User user) {
    sout("修改" + user);
}
```

```html
<!--模拟提交-->
<form action="" method="post">
    <input type="hidden" name="id" value="1"/>
    <input type="text" name="email" value="x@x.x">
    <input type="text" name="age" value="23">
    <input type="submit" value="Submit">
</form>
```

## 视图和视图解析器

无论Controller中的请求处理方法return的是String还是View还是ModelMap，最终SpringMVC都会装配成ModelAndView对象，它包含了逻辑名和模型对象的视图

SpringMVC借助视图解析器（ViewResolver）得到最终的视图对象（View），最终的视图可以是jsp，也可能是Excel、JFreeChart等各种表现形式的视图

对于最终究竟采用何种视图对模型数据进行渲染，处理器并不关心，处理器工作重点聚焦在生成模型数据的工作上，从而实现mvc的充分解耦

### 视图

视图的作用是渲染模型数据，将模型里的数据以某种形式呈现。为了实现视图模型和具体实现技术的解耦，Spring在org.springframework.web.servlet包中定义了一个高度抽象的View接口

视图对象由视图解析器负责实例化，由于视图是无状态的所以不会有线程安全的问题

### 视图解析器

SpringMVC为逻辑视图名的解析提供了不同的策略，可以在SpringWEB上下文中配置一种或多种解析策略，并指定他们之间的先后顺序。每一种映射策略对应一个具体的视图解析器实现类

视图解析器的作用很单一：将逻辑视图解析为一个具体的视图对象。所有的视图解析器都必须实现ViewResolver接口

JSP是最常见的视图技术，可以使用InternalResourceViewResolver作为视图解析器

### JstlView

如果项目中使用了JSTL，则SpringMVC会自动将视图由InternalResourceView转为JstlView；若使用JSTL的fmt标签则需要在SpringMVC配置文件中配置国际化资源文件

```xml
<!--配置国际化资源文件-->
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basename" value="i18n"/>
</bean>
```

若希望直接响应通过SpringMVC渲染的页面，可以使用mvc:view-controller标签实现`<mvc:view-controller path="xxx" view-name="success"`

配置mvc:view-controller后还需要配置mvc:annotation-driven标签`<mvc:annotation-driven ></mvc:annotation-driven>`

### 自定义视图

```java
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Component
public class ViewDemo implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print("hello view, time: " + new Date());
    }
}
```

```xml
<!--配置视图BeanNameViewResolver解析器：使用视图的名字来解析视图。通过order属性来定义视图解析器的优先级，order越小则优先级越高-->
<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
    <property name="order" value="100"/>
</bean>
```

```
@RequestMapping("/test")
public String testView(){
    sout("testView");
    return "helloView";//返回自定义视图的类名首字母小写
}
```

### 重定向

一般情况下，控制器方法返回字符串类型的值会被当成逻辑视图名处理。如果返回的字符串中带`forward:`或`redirect:`前缀时，SpringMVC会将其后的字符串作为url来处理

- forward: 重定向
- redirect: 转发

## RESTFUL CRUD

com.yinziming.part12.crud.*

src/main/webapp/WEB-INF/views/crud/*

### 处理静态资源

优雅的REST风格的资源url不希望带html或do等其他后缀

若将DispatcherServlet请求映射配置为/，则SpringMVC将捕获web容器的所有请求，包括静态资源的，SpringMVC会当初普通请求处理，从而导致找不到的错误

可以在SpringMVC的配置文件中配置`<mvc:default-servlet-handler />`
的方式解决。原理是在SpringMVC上下文中定义一个DefaultServletHttpRequestHandler，如果是没有经过映射的请求则交给web应用服务器默认的Servlet处理，如果不是静态资源的请求，才交给DispatcherServlet继续处理

一般web应用服务器默认的Servlet名称都是default，如果不是则需要通过default-servlet-name属性显式指定

```xml
<!--处理静态资源-->
<beans>
    <!--  bean略  -->
    <mvc:default-servlet-handler/><!---->
    <mvc:annotation-driven/><!---->
</beans>
```

## 数据转换

1. SpringMVC主框架将ServletRequest对象及目标方法的入参实例传递给WebDataBinderFactory实例，以创建DataBinder实例对象
2. DataBinder调用装配在SpringMVC上下文中的ConversionService组件进行数据类型转换、数据格式化操作，将Servlet中的请求信息填充到入参对象中
3. 调用Validator组件对已经绑定了请求信息的入参对象进行数据合法性校验，并最终生成数据绑定结果BindingData对象
4. SpringMVC抽取BindingResult中的入参对象和校验错误对象，将它们赋给处理方法的响应入参

### 自定义类型转换器

ConversionService是Spring类型转换体系的核心接口，可以利用ConversionServiceFactoryBean在Spring的IOC容器中定义一个ConversionService，
Spring将自动识别出IOC容器中的ConversionService，并在Bean属性配置及Spring MVC处理方法入参绑定等场合使用它进行数据的转换。
可通过ConversionServiceFactoryBean的converters属性注册自定义的类型转换器

Spring定义了三种类型的转换器接口，实现任意一个转换器接口都可以作为自定义转换器注册到ConversionServiceFactoryBean中

1. Converter<S, T> 将S类型对象转为T类型对象
2. ConverterFactory 奖项同系列多个“同质”Converter封装在一起，如果希望将一种类型的对象转换为另一种类型及其子类的对象可使用该转换器工厂类
3. GenericConverter 会根据源类对象及目标类对象所在宿主类中的上下文信息进行类型转换

```xml
<!--<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>-->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
    <property name="converters">
        <list>
            <bean class="com.yinziming.part12.crud.UserConverter"/>
        </list>
    </property>
</bean>
```

### 关于mvc:annotation-driven

它会自动注册RequestMappingHandlerMapping、ReequestMappingHandlerAdapter、ExceptionHandlerExceptionResolver三个Bean，还提供以下支持：

- 支持使用ConversonService实例对表单参数进行类型转换
- 支持使用@NumberFormatAnnotation、@DateTimeFormat注解完成数据类型的格式化
- 支持使用@Valid注解对JavaBean实例进行JSR 303验证
- 支持使用@RequestBody和@ResponseBody注解

### &#64;InitBinder

- 由&#64;InitBinder标识的方法，可以对WebDataBinder对象进行初始化。WebDataBinder是DataBinder的子类，用于完成由表单字段到JavaBean属性的绑定
- &#64;InitBinder的方法不能有返回值，必须声明为void
- &#64;InitBinder方法的参数通常是WebDataBinder

```
@InitBinder
public void initBinder(WebDataBinder dataBinder){
    dataBinder.setDisallowedFields("roleSet");//不自动绑定对象中的roleSet属性，另行处理
}
```

### Spring MVC数据的格式化

- @DateTimeFormat可对java.util.Date, java.util.Calendar, java.long.Long时间类型进行标注
    - pattern属性：类型为字符串，指定解析/格式化字段数据的模式，比如"yyyy-MM-dd hh:mm:ss"
    - iso属性：类型为DateTimeFormat.ISO，指定解析/格式化字段数据的ISO模式，包括：ISO.NONE(不使用)、ISO.DATE(yyyy-MM-dd)、 ISO.TIME(hh:mm:ss.SSSZ)
      、ISO.DATE_TIME(yyyy-MM-dd hh:mm:ss.SSSZ)
    - style属性：字符串类型，通过样式指定日期时间的格式，由两位字符组成，第一位表示日期的格式，第二位表示时间的格式
- @NumberFormat
    - style属性：类型为NumberFormat.Style。用于指定样式类型，包括Style.NUMBER正常数字类型、Style.CURRENCY货币类型、Style.PERCENT百分数类型
    - pattern属性：类型为String，自定义样式，如pattern="#,###"

### 数据校验

1. 如何校验
2. 验证出错转向哪一个页面
3. 错误消息如何显示，如何把错误消息进行格式化

#### JSR 303

是java为Bean数据合法性校验提供的标准框架，已经包含在javaEE6.0中，通过在Bean属性上标注类似于&#64;NotNull、&#64;Max等标准的注解指定校验规则，并通过标注的验证接口对bean进行验证

| 注解 | 说明 |
| ----- | ----- |
| &#64;Null | 必须为null |
| &#64;NotNull | 必须不为null |
| &#64;AssertTrue | 必须为true |
| &#64;AssertFalse | 必须为false |
| &#64;Min(value) / &#64;DecimalMin(value) | 必须是个数字且必须大于等于指定值 |
| &#64;Max(value) / &#64;DecimalMax(value0 | 必须是个数字且必须小于等于指定值 |
| &#64;Size(max, min) | 大小必须在指定的范围内 |
| &#64;Digits(integer, fraction) | 必须是一个数字，且必须在可接受的范围内 |
| &#64;Past | 必须是过去的日期 |
| &#64;Future | 必须是将来的日期 |
| &#64;Pattern(value) | 必须符合指定的正则表达式 |

#### Hibernate Validator扩展注解

| 注解 | 说明 |
| ----- | ----- |
| &#64;Email | 必须是email地址 |
| &#64;Length | 字符串的大小必须在指定范围 |
| &#64;NotEmpty | 字符串必须非空 |
| &#64;Range | 必须在合适的范围内 |

## 返回json

底层原理HttpMessageConverter

```
@ResponseBody
@RequestMapping("/testJson")
public Collection<Employee> testJson(){
    return employeeDao.getAll();
}
```

## 国际化

1. 在页面上能够根据浏览器语言设置的情况对文本、时间、数值进行本地化处理
2. 可以在bean中获取国际化资源文件Locale对应的信息
3. 可以通过超链接切换Locale，而不依赖于浏览器的语言设置情况
4. 使用JSTL的fmt标签
5. 在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法即可
6. 配置LocalResolver和LocaleChangeInterceptor

```xml
<!--配置国际化资源文件-->
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basename" value="i18n"/>
</bean>
```

```html
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<fmt:message key="i18n.user"/>
</body>
```

## 文件上传

SpringMVC通过MultipartResolver接口实现的，Spring通过Jakarta Commons FileUpload技术实现了一个CommonsMultipartResolver实现类，默认没有装配。

```xml
<!--defaultEncoding必须和用户jsp的pageEncoding属性一致-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="defaultEncoding" value="UTF-8"/>
    <property name="maxUploadSize" value="5242880"/>
</bean>
```

```
@RequestMapping("/test")
public String test(@RequestParam("file") MultipartFile file) throws IOException {
    System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
    System.out.println("file.getInputStream() = " + file.getInputStream());
    return "success";
}
```

## 拦截器

SpringMVC可以通过使用拦截器对请求进行拦截处理，用户可以自定义拦截器来实现特定的功能，自定义拦截器必须实现HandlerInterceptor接口

- preHandle() 在业务处理器处理请求之前被调用，在该方法中对用户请求request进行处理。如果对请求进行拦截处理后还要调用其他拦截器或是业务处理器去进行处理则返回true，否则返回false
- postHandle() 这个方法在业务处理器处理完请求后，DispatcherServlet向客户端返回响应之前被调用，在该方法中对用户请求request进行处理
- afterCompletion() 这个方法在DispatcherServlet完全处理完请求后被调用，可以进行一些资源清理的工作

```xml
<!--配置自定义拦截器-->
<mvc:interceptors>
    <!--    <mvc:mapping path="/xxx"/>-->
    <bean class="xxx.FirstInterceptor"/>
</mvc:interceptors>
```

## 异常处理

SpringMVC通过HandlerExceptionResolver处理程序的异常，包括Handler映射、数据绑定以及目标方法执行时发生的异常

## 运行流程

## spring整合springmvc

1. 通常情况下，数据源、事务、Service、Dao、整合其他框架都是放在spring的配置文件中，
2. 不需要在web.xml中配置启动SpringIOC容器的ContextLoaderListener，二十都放在SpringMVC配置文件中，也可以分为多个Spring的配置文件，然后使用import节点导入其他配置文件

若Spring的IOC容器和SpringMVC的IOC容器扫描的包有重合的部分，就会导致有的bean被创建两次。解决：

1. 使两者扫描的包没有重合的部分
2. 使用exclude-filter和include-filter来规定只能扫描的注解

SpringMVC的IOC容器中的bean可以来引用SpringIOC容器中的bean，反之不行

在SpringMVC配置文件中引用业务层的Bean

- 多个SpringIOC容器之间可以设置为父子关系，以实现良好的解耦
- SpringMVC Web层容器可作为业务层Spring容器的子容器，即Web层容器可以用用业务层容器的Bean，而业务层容器访问不到web层

## 与Struts2的对比

1. SpringMVC的入口是Servlet，Struts2是Filter
2. SpringMVC稍微比Struts2快，SpringMVC是基于方法，而Struts2基于类，每发一次请求都会实例一个Action
3. SpringMVC使用更加简洁，开发效率高些，支持JSR303，处理ajax请求更方便
4. Struts2的OGNL表达式使页面的开发效率比SpringMVC高些
