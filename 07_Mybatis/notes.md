# Mybatis

## demo

src/test/java/com/yinziming/part00/demo/MybatisTest.java

- test1() "hello world"
- test2() Mapper接口式编程

## 全局配置文件

Configuration配置

- properties 属性 mybatis可以通过properties标签来引入外部properties配置文件的内容
- settings 设置 运行时行为设置 https://mybatis.org/mybatis-3/configuration.html#settings
- typeAliases 类型命名 别名处理器，可以为java类起别名，不区分大小写 也可以使用&#64;Alias注解在类上起别名
- typeHandlers 类型处理器 关于日期和时间的处理，mybatis3.4以前的版本需要手动注册JSR310，以后的版本都是自动注册的
    - 实现org.apache.ibatis.type.TypeHandler接口或继承org.apache.ibatis.type.BaseTypeHandler
    - 指定其映射某个jdbc类型（可选）
    - 在mybatis全局配置文件中注册
- objectFactory 对象工厂
- plugins 插件
- environments 环境
    - environment 环境变量
        - transactionManager 事务管理器
        - dataSource 数据源
- databaseIdProvider 数据库厂商标识
- mappers 映射器

## 映射文件

- cache 命名空间的二级缓存配置
- cache-ref 其他命名空间缓存配置的引用
- resultMap 自定义结果集映射
- parameterMap 已弃用 老师风格的参数映射
- sql 抽取可重用语句块
- insert 映射插入语句
- update 映射更新语句
- delete 映射删除语句
- select 映射查询语句

### 获取自增主键的值

在`insert`标签中添加参数`useGeneratedKeys="true"`和`keyProperty="id"`

### Mapper的方法中参数的处理

- 单个参数，mybatis不会做特殊处理
- 多个参数，mybatis会把多个参数封装成一个map，最好在各个参数之前加上注解&#64;Param("自定义名称")。也可以直接将一个map作为方法的参数。
  如果多个参数不是业务模型中的数据但是经常要使用，推荐编写一个TO（Transfer Object）数据传输对象，比如`Page{int index, size;}`

### 更多例子

```
(@param("id") Integer id, String lastname)
    id ==> #{id/param1}     lastname ==> #{param2}
    
(Integer id, Employee e)
    id ==>#{param1}         lastname ==> #{param2.lastname}
    
(List<Integer> ids)//对于集合类或数组，mybatis也会封装成map
    第一个id的值 ==> #{list[0]}
```

### &#35;和&#36;的区别

&#35;：在sql语句中使用占位符，有一些更丰富的用法，比如规定参数的一些规则

- javaType
- jdbcType 在数据为null的时候有些数据库可能不能识别mybatis对null的默认处理（比如Oracle）
- mode
- numericScale
- resultMap
- typeHandler
- jdbcTypeName
- ~~expression~~（未来准备支持的功能）

&#36;：在sql语句中直接填充变量值，适用于不能使用占位符的场景

### 查询返回List

com.yinziming.part02.mappers.EmployeeMapperTest.testGetAll()

### 查询返回Map

- 属性名 = 属性值：com.yinziming.part02.mappers.EmployeeMapperTest.testGetMap()
- 自定义key = 某种对象：com.yinziming.part02.mappers.EmployeeMapperTest.testGetMapAll()

### 自定义resultMap

1. resultMap自定义结果集映射规则
    - com.yinziming.part02.mappers.EmployeeMapperTest.testGetByIdByResultMap()
2. resultMap_关联查询_association定义关联对象封装规则

```xml
<!--association定义关联对象封装规则-->
<resultMap type="com.yinziming.part02.mappers.Employee" id="xxx">
    <id column="id" property="id"/>
    <result column="gender" property="gender"/>
    <association property="dept" javaType="com.yinziming.part02.mappers.Department">
        <id column="department_id" property="id"/>
        <result column="department_name" property="name"/>
    </association>
</resultMap>
```

3. **association**分步查询
    - com.yinziming.part02.mappers.EmployeeMapperTest.testByStep()

4. association延迟加载

```xml
<!-- mybatis-config.xml -->
<settings>
    <setting name="jdbcTypeForNull" value="NULL"/><!--默认是OTHER，如果不改，对于Oracle数据库会有问题-->
    <setting name="lazyLoadingEnabled" value="true"/>
    <setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

5. **collection**定义关联集合封装规则：查询部门的时候将部门里所有的员工都查出来
    - com.yinziming.part02.mappers.EmployeeMapperTest.testGetByIdPlus()
    - com.yinziming.part02.mappers.EmployeeMapperTest.testGetByIdStep()

6. 分步查询传递多列值、fetchType

`<association ... column="{key1=col1,key2=col2}"/>`

fetchType="lazy|eager"，lazy表示使用延迟加载，eager表示立即加载

7. discriminator鉴别器
    - mybatis可以根据discriminator判断某列的值，根据这个值改变封装行为。例子：如果查出的是女生(gender=1)就把部门信息查询出来，否则把lastname赋值给email
    - com.yinziming.part02.mappers.EmployeeMapperTest.testGetEmployByDiscriminator()

## 动态sql

- if
    - com.yinziming.part03.dynamic_sql.EmployeeMapperDynamicSQLTest.testGetByConditionIf()
- choose (when, otherwise)
    - com.yinziming.part03.dynamic_sql.EmployeeMapperDynamicSQLTest.testGetByConditionChoose()
- trim (where, set) 自定义字符串的截取规则
    - com.yinziming.part03.dynamic_sql.EmployeeMapperDynamicSQL.getByConditionTrim()
- foreach
    - com.yinziming.part03.dynamic_sql.EmployeeMapperDynamicSQLTest.testGetForEach()
    - com.yinziming.part03.dynamic_sql.EmployeeMapperDynamicSQLTest.testAddForEach()

mybatis不推荐where条件用and或者or拼接，但是只能去掉每条开头的and或者or，放在其他位置就不能自动删除

### 内置参数

- _parameter: 代表整个参数，单个参数时就是这个参数，多个参数时就是被封装成的map
- _databaseId: 如果配置了DatabaseProvider标签，就代表当前数据库的别名

### bind

bind可以将OGNL表达式的值绑定到一个变量中，方便后来引用

```
<select>
    <bind name="_lastname" value="'%' + lastname + '%'"/>
    select * from employee where lastname like #{_lastname}
</select>
```

### sql标签

抽取可重用的sql片段，方便以后用include引用。include还可以自定义一些property，sql标签内部就能使用自定义的属性 ${property_name}，注意不是#

```
<sql id="insertColumn">
    id, lastname, email, gender
</sql>
<insert>
    <include refid="insertColumn"/>
</insert>
```

## 缓存机制

mybatis系统中默认定义了两级缓存

- 默认情况下只有一级缓存（SqlSession级别的缓存，也称为本地缓存）开启，底层是个map
- 二级缓存需要手动开启和配置，是基于namespace级别的缓存
- 为了提高扩展性，mybatis定义了缓存接口Cache，可以通过实现Cache接口来自定义二级缓存

### 一级缓存失效的四种情况

1. 同一个SqlSession对应同一个一级缓存，如果是不同的SqlSession那就不是同一个一级缓存
2. SqlSession相同，查询条件不同
3. SqlSession相同，两次查询之间有增删改操作
4. SqlSession相同，但手动清空了一级缓存

### 二级缓存

工作机制：

1. 一个会话查询一条数据，这个数据就会放在当前会话的一级缓存中
2. 如果会话关闭，一级缓存中的数据就保存到二级缓存中。新会话首先会在二级缓存中查找

使用：

1. 开启全局二级缓存配置`<setting name="cacheEnabled" value="true"/>`
2. 在每个mapper.xml中开启二级缓存：在`mapper`标签中加入`cache`标签即可，cache有一些属性可以指定：
    - eviction 缓存的回收策略，默认是LRU（最近最少使用）
    - flushInterval 刷新间隔，单位毫秒，不设置就没有刷新间隔，缓存仅仅调用语句时刷新
    - readOnly 如果设为true会给调用者返回缓存对象的相同实例，因此这些对象不能被修改，性能高些；如果false会返回对象的拷贝，性能弱些但是安全，默认false
    - size 引用数目（正整数）代表缓存最多可以存储多少个对象，太大容易OOM
    - type 指定自定义缓存的全类名，实现Cache接口的类
3. bean对象需要实现序列化接口
4. 第二次查之前需要先将第一次的SqlSession提交或关闭，否则还是会查两次

### 和缓存有关的设置和属性

1. cacheEnabled = true | false：开启或关闭二级缓存（一级缓存不受影响）
2. mapper.xml中的select标签有useCache属性，如果设置为false则不使用二级缓存（一级缓存不受影响）
3. mapper.xml中的增删改查标签有flushCache属性，如果设置为true会清空一级和二级缓存。增删改的默认为true，查的默认为false
4. SqlSession.clearCache()只是清除当前session的一级缓存
5. 全局配置文件中的localCacheScope（mybatis3.3+）：本地缓存（一级）作用域，两种取值：SESSION（默认）、STATEMENT

### 第三方缓存

ehcache`<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>`

## 整合Spring

```xml
<!-- web.xml -->
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--Spring配置-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!--SpringMVC配置-->
    <servlet>
        <servlet-name>springDispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springDispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

```xml
<!-- applicationContext.xml -->
<beans>
    <!--让Spring扫描除了Controller以外的组件-->
    <context:component-scan base-package="com.yinziming.part05">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    <!--配置Spring用来空值业务逻辑、数据源、事务控制、aop等-->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/><!--事务管理器-->
    </bean>
    <tx:annotation-driven transaction-manager="transactionManager"/>
    <!--整合Mybatis   目的：1. @Autowire 2. 管理事务-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.yinziming.part05.integration.dao"/>
    </bean>
</beans>
```

```xml
<!-- SpringMVC部分 spring-servlet.xml -->
<beans>
    <!-- 只扫描控制器 -->
    <context:component-scan base-package="com.yinziming.part05" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    <mvc:annotation-driven/>
    <mvc:default-servlet-handler/>
    <!--视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

## 逆向工程 mybatis generator

com.yinziming.part06.reverse.MybatisTest()

## 运行原理

1. 获取SqlSessionFactory对象
    1. 创建SqlSessionFactoryBuilder对象
    2. build(inputStream)
    3. 创建XML解析器parser
    4. 解析每一个标签，把详细信息保存在Configuration中
    5. 解析mapper.xml，将增删改查封装成MappedStatement，一个MappedStatement就代表一个增删改查的详细信息
    6. 返回封装了所有配置信息的Configuration
    7. return new DefaultSqlSessionFactory(Configuration)，也即生成的SqlSessionFactory对象
2. 获取SqlSession对象
    1. sqlSessionFactory.openSession()
    2. openSessionFromDataSource()
    3. 获取一些信息，创建事务tx
    4. newExecutor()
    5. 根据Executor在全局配置中的类型，创建出SimpleExecutor / ReuseExecutorBatchExecutor
    6. 如果开启二级缓存，创建CachingExecutor
    7. executor = (Executor) interceptorChain.pluginAll(executor);添加所有拦截规则
    8. return new DefaultSqlSession(configuration, executor, autoCommit)，返回SqlSession的实现类DefaultSqlSession的对象
3. 获取Mapper接口的代理对象
    1. org.apache.ibatis.session.defaults.DefaultSqlSession.getMapper(Class<T> type)
    2. org.apache.ibatis.session.Configuration.getMapper(type, this)
    3. org.apache.ibatis.binding.MapperRegistry.getMapper(type, sqlSession)
    4. 根据接口类型获取MapperProxyFactory
    5. mapperProxyFactory.newInstance(sqlSession)
    6. 创建mapperProxy（实现了InvocationHandler）
    7. mapperProxy.newInstance(mapperProxy)创建mapper的代理类对象
4. 执行mapper中的方法
    1. mapperProxy.invoke()
    2. 判断是增删改查的哪一个
    3. 包装参数为map或参数只有一个时直接返回参数
    4. sqlSession.selectOne()
    5. 调用selectList()
    6. 获取MappedStatement ms
    7. executor.query(ms, wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
    8. 通过ms.getBoundSql(parameterObject)得到boundSql，它代表sql语句的详细信息
    9. CachingExecutor.query(ms, parameterObject, rowBounds, resultHandler, key, boundSql)
    10. 查看本地缓存中是否有数据，如果没有就调用queryFromDatabase()
    11. doQuery()
    12. 创建StatementHandler对象，用于生成prepareStatement
    13. statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler)
    14. 预编译sql产生PreparedStatement对象
    15. 调用ParameterHandler设置参数
    16. 调用TypeHandler给sql预编译设置参数
    17. 查出数据，使用ResultSetHandler处理结果，使用TypeHandler获取value值
    18. 后续的数据库连接关闭流程
    6. 返回列表第一个，或列表为空返回null，或在查询结果不止一个时抛异常

### Mybatis流程总结

1. 根据配置文件（全局、mapper）初始化出Configuration对象
2. 创建一个DefaultSqlSession对象，里面包含Configuration和Executor（根据配置文件中的defaultExecutorType创建对应的Executor
3. DefaultSqlSession.getMapper()拿到Mapper接口对应的MapperProxy
4. MapperProxy里面有DefaultSqlSession
5. 执行增删改查方法
    1. 调用DefaultSqlSession的增删改查（Executor）
    2. 创建一个StatementHandler对象，同时也创建出ParameterHandler和ResultSetHandler
    3. 调用StatementHandler预编译参数以及设置参数值，使用ParameterHandler来给sql设置参数
    3. 调用StatementHandler的增删改查方法
    5. ResultSetHandler封装结果

注意：四大对象创建的时候都有一个interceptorChain.pluginAll(parameterHandler)

## 插件

### 原理

1. 在四大对象创建的时候，每个创建出来的对象不是直接返回的，而是interceptorChain.pluginAll(parameterHandler)
2. 获取到所有的Interceptor，调用interceptor.plugin(target)，返回target包装后的对象
3. 插件机制使得我们可以使用插件为目标对象创建一个代理对象，代理对象就可以拦截到四大对象的每一个执行方法

### 编写流程

1. 编写Interceptor的实现类，完成插件签名
2. 在全局配置文件中添加plugin标签

### 多个插件运行流程

创建动态代理的时候是按照配置文件中的顺序创建层层代理对象，执行的时候按照逆向顺序执行

### 使用场景

1. PageHelper插件进行分页
2. 批量操作
4. 存储过程（Oracle）
4. typeHandler处理枚举

