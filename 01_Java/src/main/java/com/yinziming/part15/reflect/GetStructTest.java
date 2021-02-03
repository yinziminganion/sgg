package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class GetStructTest {
    @Test
    public void getField1() {
        Class<Dog> clazz = Dog.class;
        Field[] fields = clazz.getFields();
        for (var f : fields) {
            System.out.println(f);//会输出当前运行时类及其父类中声明为public的属性
        }
        System.out.println();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (var f : declaredFields) {
            System.out.println(f);//获取当前运行时类的所有属性，与权限修饰符无关（不包含父类中的）
        }
    }

    @Test
    public void getField2() {
        Class<Dog> clazz = Dog.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (var f : declaredFields) {
            //权限修饰符
            int modifiers = f.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            //数据类型
            Class<?> type = f.getType();
            System.out.print(type.getName() + " ");
            //变量名
            String name = f.getName();
            System.out.println(name);
        }
    }
}

class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breathe() {
        System.out.println("breathing");
    }

    public void eat() {
        System.out.println("eating");
    }
}

@MyAnnotation("Dog")
class Dog extends Creature<String> implements Comparable<String>, MyInterface {
    private String name;
    int age;
    public int id;

    @MyAnnotation("public Dog()")
    public Dog() {
    }

    @MyAnnotation("private Dog(String name)")
    private Dog(String name) {
        this.name = name;
    }

    @MyAnnotation("Dog(string name, int age)")
    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("info");
    }

    @MyAnnotation("private String show(String s)")
    private String show(String s) {
        System.out.println("show " + s);
        return new StringBuffer(s).reverse().toString();
    }

    public String display(String s, int age) throws IOException {
        return s.repeat(3) + " " + age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    private static void staticMethod() {
        System.out.println("staticMethod()");
    }
}

interface MyInterface {
    void info();
}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "my annotation";
}