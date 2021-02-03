package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 获取运行时类的方法结构
 */
public class MethodTest {
    @Test
    public void test1() {
        Class<Dog> clazz = Dog.class;
        Method[] methods = clazz.getMethods();//当前运行时类及其父类的public方法
        for (var m : methods) {
            System.out.println(m);
        }
        System.out.println();

        Method[] declaredMethods = clazz.getDeclaredMethods();//当前运行时类的所有方法（无所谓权限，不包含父类的）
        for (var m : declaredMethods) {
            System.out.println(m);
        }
    }

    @Test
    public void test2() {
        Class<Dog> clazz = Dog.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (var m : declaredMethods) {
            //获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            for (var a : annotations) {
                System.out.println(a);
            }

            //获取权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + " ");
            //返回值类型
            System.out.print(m.getReturnType().getName() + " ");
            //方法名
            System.out.print(m.getName() + "(");
            //形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            for (var i = 0; i < parameterTypes.length; i++) {
                if (i == parameterTypes.length - 1) {
                    System.out.print(parameterTypes[i].getName() + " args_" + i);
                } else {
                    System.out.print(parameterTypes[i].getName() + " args_" + i + ", ");
                }
            }
            System.out.print(')');
            //异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print(" throws ");
                for (var i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                    } else {
                        System.out.print(exceptionTypes[i].getName() + ", ");
                    }
                }
            }
            System.out.println();
        }
    }
}
