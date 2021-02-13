# 事务

## 四个特性ACID

- 原子性
- 一致性
- 隔离性
- 持久性

## 准备工作

1. 创建数据库表，添加记录
2. 创建service，dao等
3. 在dao创建多钱和少钱两个方法，service创建转账方法

## 步骤

1. 开启事务，事务要添加到service层
2. 进行业务操作。两种方式：编程式事务管理和声明式事务管理（常用）
3. 如果没有异常，提交事务。如果出现异常，事务回滚

### API

Spring进行声明式事务管理，底层使用AOP

PlatformTransactionManager接口，jdbc的实现类是DataSourceTransactionManager

## 操作

1. 在spring配置文件中配置事务管理器

2. 在配置文件中开启事务注解，引入名称空间tx

3. 在service类或其方法上添加事务注解@Transactional

### 事务注解的参数

1. propagation 事务传播行为
    - 多事务方法直接进行调用，这个过程事务是如何进行管理的

2. isolation 事务隔离级别
    - 事务有隔离性，多事务操作之间不产生影响。不考虑隔离性会出现脏读、不可重复读、幻读

3. timeout 超时时间，默认-1代表不设置超时时间

2. readOnly 是否只读

2. rollbackFor 回滚

2. noRollbackFor 不回滚

#### Spring中事务的7种传播行为

| 传播属性 | 描述 | 
| :----: | :----: |
| REQUIRED | 如果有事务在运行，当前的方法就在这个事务内运行，否则，就启动一个新的事务，并在自己的事务内运行 |
| REQUIRED_NEW | 当前的方法必须启动新事务，并在它自己的事务内运行．如果有事务正在运行，应该将它挂起 |
| SUPPORTS | 如果有事务在运行，当前的方法就在这个事务内运行.否则它可以不运行在事务中 |
| NOT_SUPPORTED | 当前的方法不应该运行在事务中．如果有运行的事务，将它挂起 |
| MANDATORY | 当前的方法必须运行在事务内部，如果没有正在运行的事务，就抛出异常 |
| NEVER | 当前的方法不应该运行在事务中．如果有运行的事务，就抛出异常 |
| NESTED | 如果有事务在运行，当前的方法就应该在这个事务的嵌套事务内运行．否则，就启动一个新的事务，并在它自己的事务内运行. |

#### 数据库4种隔离级别

| 级别 | 脏读 | 不可重复读 | 幻读 | 
| :----: | :----: | :----: | :----: |
| READ_UNCOMMITTED | 有 | 有 | 有 |
| READ_COMMITTED | 无 | 有 | 有 |
| REPEATABLE_READ | 无 | 无 | 有 |
| SERIALIZABLE | 无 | 无 | 无 |

### xml方式

1. 在spring配置文件中配置事务管理器、通知、切入点和切面

```text
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd   http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <context:component-scan base-package="com.yinziming.part04.transaction"/>
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--    事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:advice id="advice">
        <tx:attributes>
            <tx:method name="trans" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    <aop:config>
        <aop:pointcut id="pt" expression="execution(* com.yinziming.part04.transaction.UserService.*(..)"/>
        <aop:advisor advice-ref="advice" pointcut-ref="pt"/>
    </aop:config>
</beans>
```

### 完全注解方式

```java

@Configuration
@ComponentScan(basePackages = "com.yinziming.part04.transaction")
@EnableTransactionManagement
public class TransactionConfig {
    @Bean
    public DataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("...");
        dataSource.setUrl("...");
        dataSource.setUsername("...");
        dataSource.setPassword("...");
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
```










