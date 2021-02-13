# JDBC Template

## 概念

Spring对JDBC进行了封装，使用JdbcTemplate方便实现对数据库的操作

步骤：

1. 引入依赖
2. 在spring配置文件中配置数据库连接池
3. 创建JdbcTemplate对象，注入DataSource
3. 创建Service类，创建Dao类，在dao注入JdbcTemplate对象，修改配置文件

## 操作

### 添加

1. 创建实体类
2. 编写service和dao

### 修改和删除

- 修改
- 删除

### 查

1. 查询某个值

2. 查询返回对象

3. 查询多个对象

### 批量添加

batchUpdate(String sql, List<Object[]> bachArgs)




