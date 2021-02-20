package com.yinziming.part07.plugin.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * 完成插件签名，告诉mybatis当前插件用来拦截哪个对象的哪个方法，将写好的插件注册到全局配置文件
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)})
public class MyFirstPlugin implements Interceptor {
    /**
     * 拦截目标对象的目标方法的执行
     *
     * @return 执行后的返回值
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("com.yinziming.part07.plugin.dao.MyFirstPlugin.intercept(Invocation invocation) " + invocation.getMethod());
        return invocation.proceed();
    }

    /**
     * 包装目标对象，为目标对象创建一个代理对象
     *
     * @return 包装后的对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("com.yinziming.part07.plugin.dao.MyFirstPlugin.plugin(Object target) target = " + target);
        return Plugin.wrap(target, this);
    }

    /**
     * 将插件注册时的property属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("com.yinziming.part07.plugin.dao.MyFirstPlugin.setProperties(Properties properties) properties = " + properties);
    }
}
