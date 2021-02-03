package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 */
public class DynamicProxyTest {
    @Test
    public void test1() {
        Superman superman = new Superman();
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superman);
        proxyInstance.eat("food");
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
    }

    @Test
    public void test2() {
        Nike nike = new Nike();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nike);
        proxyClothFactory.produce();
    }
}

class ProxyFactory {
    public static Object getProxyInstance(Object object) {//object就是被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object object;

    public void bind(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil util = new HumanUtil();
        util.method1();
        Object invoke = method.invoke(object, args);
        util.method2();
        return invoke;
    }
}

interface Human {
    String getBelief();

    void eat(String food);
}

class Superman implements Human {

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("eating " + food);
    }
}

class HumanUtil {
    public void method1() {
        System.out.println("method1()");
    }

    public void method2() {
        System.out.println("method2()");
    }
}