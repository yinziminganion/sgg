# IOC

## IOC概念和底层原理

### 概念

控制反转，把对象创建和对象之间的调用过程，交给Spring进行管理，目的是降低耦合度

### 底层原理

原始方案：UserService <-> UserDao

工厂模式方案：UserService <-> UserFactory <-> UserDao IOC：

IOC：xml解析、工厂模式、反射

```xml
<!--第一步 xml配置文件，配置创建的对象-->
<bean id="dao" class="xxx.UserDao"/>
```

```java
//第二步 有Service类和Dao类，创建工厂类
class UserFactory {
    public static UserDao getDao() {
        String name = class属性值;//1. xml解析
        Class<UserDao> clazz = Class.forName(name);//2. 通过反射创建对象
        return clazz.getConstructor().newInstance();
    }
}
```

### IOC 接口

1. IOC思想基于IOC容器完成，IOC容器底层就是对象工厂
2. Spring提供IOC容器实现的两种方式：（两个接口）
    - BeanFactory：IOC容器基本实现，是Spring内部的使用接口，一般不使用。加载配置文件的时候不会创建对象，获取对象时才会创建。
    - ApplicationContext：BeanFactory的子接口，提供更多更强大的功能。加载配置文件时就会创建对象。
      （两个主要的实现类：FileSystemXmlApplicationContext，ClassPathXmlApplicationContext）

## IOC操作Bean管理

1. 什么是Bean管理（两个操作）
    - Spring创建对象
    - Spring注入属性

2. Bean管理操作
    - 基于xml
    - 基于注解

### xml方式

#### 创建对象

在Spring配置文件中，使用bean标签，标签里面添加对应属性，就可以实现对象创建，默认使用无参的构造方法

```xml
<!--
id属性：唯一标识
class属性：类的全路径
-->
<bean id="dao" class="xxx.UserDao"/>
```

#### 注入属性

DI：依赖注入，就是注入属性（setter方法，有参构造器等）

1. setter

```xml
<!--基于set-->
<bean id="book" class="xxx">
    <property name="name" value="name1"/>
    <property name="author" value="author1"/>
</bean>

        <!--扩展：p名称空间注入写法  记得添加xmlns="http://www.springframework.org/schema/p"-->
        <!--    <bean id="book" class="xxx" p:name="name1" p:author="author1"/>-->
```

```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookTest {
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Book book = context.getBean("book", Book.class);
    }
}
```

2. 构造器

```xml
<!--有参数构造注入属性-->
<bean id="orders" class="xxx">
    <constructor-arg name="name" value="abc"/>
    <constructor-arg name="address" value="cn"/>
</bean>
```

```java
public class Orders {
    private String name;
    private String address;

    public Orders(Stirng name, String address) {
        this.name = name;
        this.address = address;
    }
}

public class OrdersTest {
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Orders order = context.getBean("book", Orders.class);
    }
}
```

3. 其他类型属性
    1. 字面量
        1. 空值
        2. 特殊符号
    2. 注入属性
        1. 外部Bean（见UserService.java、UserDao.java，BeanTest.test1()）
            - 创建两个类：Service类和Dao类
            - 在Service调用Dao里的方法
            - 在Spring配置文件中进行配置
        2. 内部Bean（见Employee.java，BeanTest.test2()）
            - 一对多关系：部门和员工
            - 在实体类之间表示一对多关系
        3. 级联赋值
        4. 集合（见Student.java，BeanTest.test3()）

```xml
<!--空值-->
<property name="address">
    <null/>
</property>
```

```xml
<!--特殊符号，CDATA转义-->
<property name="address">
    <value><![CDATA[<<>>]]></value>
</property>
```

4. 工厂Bean（FactoryBean）（MyBean.java，BeanTest.test4()）
    1. 普通Bean：在配置文件中定义的bean类型就是返回类型
    1. 工厂Bean：在配置文件中定义的bean类型可以和返回类型不一样
        1. 创建类，让这个类作为工厂bean，实现接口FactoryBean
        2. 实现接口里的方法，在实现的方法中定义返回的Bean类型

### Bean作用域

1. 在Spring里面，设置创建bean实例是单实例还是多实例
2. 在Spring里面，默认情况下创建的bean实例是一个单实例对象
3. 如何设置单实例或多实例
    - singleton
    - prototype
    - request
    - session

```xml
<!--设置成singleton（或没设置时）加载配置文件的时候就会创建单例对象，是prototype时在调用getBean方法时才会创建-->
<beans>
    <bean id="xx" class="xxx" scope="singleton"/><!--单实例-->
    <bean id="yy" class="yyy" scope="prototype"/><!--多实例-->
</beans>
```

### Bean生命周期

1. 生命周期：从对象创建到对象销毁的过程
2. bean生命周期
    1. 通过无参构造器去创建bean实例
    2. 为bean的属性设置值或对其他bean的引用（调用set方法）
    3. 把bean实例传递给bean后置处理器的方法
    3. 调用bean的初始化的方法（需要进行配置）
    3. 把bean实例传递给bean后置处理器的方法
    3. bean可以使用了（对象获取到了）
    3. 当容器关闭时，调用Bean的销毁的方法（需要进行配置）

3. 演示（Orders.java，BeanTest.test5()）
4. bean的后置处理器（第3步和第5步）
    1. 创建类，实现接口BeanPostProcessor，创建后置处理器
    2. 配置xml

### 自动装配：根据指定装配规则（属性名称或属性类型），Spring自动将匹配的属性值进行注入

（autowire/Employee.java，BeanTest.test6()）

### 引入外部属性文件

（BeanTest.test7()）

### 基于注解

#### 创建对象

1. 用的注解
    1. @component
    2. @Service
    3. @Controller
    3. @Repository
2. 步骤（annotation.UserService，BeanTest.testAnnotation1()）
    1. 添加Spring的aop的jar包
    2. 开启组件扫描
    3. 创建类，在类上面添加创建对象注解

#### 属性注入

1. 用的注解
    1. @Autowired 根据属性类型进行自动装配
    2. @Qualifier 根据属性名称进行注入，与Autowired搭配使用，适用于有多个实现类时指定使用哪一个
    3. @Resource 可以根据类型注入，也可以根据名称注入
    4. @Value 注入普通类型属性
2. 步骤
    1. 创建UserServiceImpl和UserDao对象，添加创建对象的注解
    2. 在Service注入Dao对象

#### 纯注解开发

（BeanTest.testAnnotation2()）

1. 创建配置类，替代xml配置文件








