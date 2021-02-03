package com.yinziming.part17.java9;

public class InterfacePrivateMethodTest {

}

interface MyInterface {
    void abstractMethod();

    static void staticMethod() {
    }

    default void defaultMethod() {
    }

    private void privateMethod() {/*jdk9中允许接口定义私有方法*/}
}

class MyInterfaceImpl implements MyInterface {
    public static void main(String[] args) {
        MyInterface.staticMethod();//接口中的静态方法只能由接口自己调用
//        MyInterfaceImpl.staticMethod();//接口的实现类不能调用接口的静态方法
    }

    @Override
    public void abstractMethod() {

    }

    /*@Override
    public void defaultMethod() {

    }*/
}