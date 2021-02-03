package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExecuteTest {
    @Test
    public void testField() throws Exception {
        Class<Dog> clazz = Dog.class;
        Dog dog = clazz.newInstance();
        Field id = clazz.getField("id");//需要属性声明为public
        id.set(dog, 1001);
        Object id1 = id.get(dog);
        System.out.println(dog);

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);//修改private的属性需要加上
        name.set(dog, "Password");
        Object age1 = id.get(dog);
        System.out.println(dog);
    }

    @Test
    public void testMethod() throws Exception {
        Class<Dog> clazz = Dog.class;
        Dog dog = clazz.newInstance();
        Method show = clazz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        Object args = show.invoke(dog, "args");
        System.out.println(args);

        System.out.println();

        Method staticMethod = clazz.getDeclaredMethod("staticMethod");
        staticMethod.setAccessible(true);
        Object invoke = staticMethod.invoke(Dog.class/*也能直接放个null*/);
        System.out.println(invoke);//没有返回值则返回null
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Dog> clazz = Dog.class;
        //private Dog(String name)
        Constructor<Dog> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Dog dog = declaredConstructor.newInstance("Name");
        System.out.println(dog);
    }
}
