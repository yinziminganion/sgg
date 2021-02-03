package com.yinziming.part15.reflect;

/**
 * 静态代理举例
 * <p>
 * 特点：代理类和被代理类在编译期间就确定下来了
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Nike nike = new Nike();//创建被代理类的对象
        ClothFactory proxyClothFactory = new ClothFactoryProxy(nike);//创建代理类的对象
        proxyClothFactory.produce();//
    }
}

interface ClothFactory {
    void produce();
}

class ClothFactoryProxy implements ClothFactory {
    private ClothFactory factory;//用被代理类对象进行实例化

    public ClothFactoryProxy(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produce() {
        System.out.println("代理工厂做一些准备工作");
        factory.produce();
        System.out.println("代理工厂做一些后续工作");
    }
}

class Nike implements ClothFactory {
    @Override
    public void produce() {
        System.out.println("Nike工厂生产中");
    }
}