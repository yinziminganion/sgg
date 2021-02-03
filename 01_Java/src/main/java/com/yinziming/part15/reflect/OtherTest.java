package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {
    @Test
    public void testGetConstructor() {
        Class<Dog> clazz = Dog.class;
        Constructor<?>[] constructors = clazz.getConstructors();//获取当前运行时类中声明为public的构造器
        for (var c : constructors) {
            System.out.println(c);
        }
        System.out.println();

        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();//获取当前运行时类中声明的所有构造器
        for (var c : declaredConstructors) {
            System.out.println(c);
        }
    }

    @Test
    public void testGetParent() {
        Class<Dog> clazz = Dog.class;
        Type genericSuperclass = clazz.getGenericSuperclass();//获取当前运行时类的带泛型的父类
        System.out.println(genericSuperclass);

        System.out.println();

        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();//获取当前运行时类的带泛型的父类的泛型(场景：DAO)
        for (var a : actualTypeArguments) {
            System.out.println(a);
        }
    }

    @Test
    public void testGetInterface() {
        Class<Dog> clazz = Dog.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (var i : interfaces) {
            System.out.println(i);
        }

        System.out.println();

        Class<?>[] interfaces1 = clazz.getSuperclass().getInterfaces();//获取父类的接口
        for (var i : interfaces1) {
            System.out.println(i);
        }
    }

    @Test
    public void testGetPackage() {
        Class<Dog> clazz = Dog.class;
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);
    }

    @Test
    public void testGetAnnotations() {
        Class<Dog> clazz = Dog.class;
        Annotation[] annotations = clazz.getAnnotations();//获取当前运行时类声明的注解
        for (var a : annotations) {
            System.out.println(a);
        }


    }
}
