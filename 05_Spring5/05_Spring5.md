# Spring5新功能

1. 基于java8，删除了很多不建议使用的类和方法
2. spring5自带了通用的日志封装，移除了Log4jConfigListener，官方建议使用Log4j2
3. 支持@Nullable注解
4. 支持函数式风格GenericApplicationContext
5. spring5支持整合JUnit5

```java

@ExtendWith(SpringExtention.class)
@ContextConfiguration("classpath:bean1.xml")
public class JTest4 {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.accountMoney();
    }
}
```

## SpringWebflux

1. 介绍
    - 是spring5新添加的模块，用于web开发，功能与SpringMVC类似，Webflux是用当前一种比较流行的响应式编程编写的框架
    - 传统web框架如SpringMVC基于Servlet容器，Webflux是一种异步非阻塞的框架，异步非阻塞在Servlet3.1以后才支持，核心是基于Reactor的相关API实现的
    - Webflux优势：①非阻塞，在有限资源下提高系统吞吐量和伸缩性，以Reactor为基础实现响应式编程②函数式编程，Spring5基于Java8，可以使用函数式编程方式实现路由请求
    - 两个框架都可以使用注解方式，都运行在Tomcat等容器中；SpringMVC采用命令式编程，Webflux使用异步响应式编程

2. 响应式编程
    - 响应式编程是一种面向数据流和变化传播的编程范式。这意味着可以在编程语言中很方便地表达静态或动态的数据流，而相关的计算模型会自动将变化的值通过数据流进行传播。
    - Java8及其之前版本，提供了观察者模式的两个类Observer和Observable(ObserverDemo.java)
    - Java9 Flow(FlowDemo.java)
    - Reactor实现(ReactorDemo.java)
        - 响应式编程操作中，需要满足Reactive规范
        - Reactor有两个核心类，Mono（实现发布者，返回0或1个元素）和Flux（实现发布者，返回N个元素），都实现了Publisher接口
        - Flux和Mono都是数据流的发布者，使用Flux和Mono都可以发出三种数据信号：①元素值②错误信号③完成信号，错误和完成都代表终止信号
3. Webflux执行流程和核心API
    - Netty
    - SpringWebflux执行过程和SpringMVC是类似的，SpringWebflux核心控制器DispatcherHandler，实现接口WebHandler
    - DispatcherHandler负责请求的处理；HandlerMapping请求查询到处理的方法，HandlerAdapter真正负责请求处理，HandlerResultHandler响应结果处理
    - SpringWebflux实现函数式编程的两个接口：RouterFunction路由处理，HandlerFunction处理函数
4. 基于注解编程模型
    - WebfluxDemoApplication.java
5. 基于函数式编程模型
    - 在使用函数式编程模型操作的时候，需要自己初始化服务器
    - 基于函数式编程模型有两个核心接口：RouterFunction（实现路由功能，请求转发给对应的handler）和HandlerFunction（处理请求生成响应的函数）。核心任务定义两个函数式接口的实现并且启动需要的服务器
    - SpringWebflux请求和响应不再是ServletRequest和ServletResponse，而是ServerRequest和ServerResponse
























