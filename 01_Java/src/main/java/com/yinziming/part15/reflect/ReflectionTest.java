package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对于java.lang.Class的理解
 * 1.类的加载过程
 * 程序经过javac命令会生成.class结尾的字节码文件，接着通过java命令对某个字节码文件进行解释运行，把字节码文件加载到内存
 * 此过程就成为类的加载。加载到内存中的类，我们成为运行时类，此运行时类就作为Class的一个实例
 * 2.换句话说，Class的实例就对应着一个运行时类
 */
public class ReflectionTest {//https://www.bilibili.com/video/BV1Kb411W75N?p=636

    @Test
    public void test1() {
        //反射之前，对于Person类的一些操作
        Person p = new Person("Tom", 22);
        p.age = 10;
        System.out.println(p);
        p.show();
    }

    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //反射对于Person类的一些操作
        Class clazz = Person.class;
        //通过反射创建Person类的对象
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("Tom", 22);
        Person p = (Person) obj;
        System.out.println(p);

        //通过反射调用对象指定的属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p);

        //通过反射调用对象指定的方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //通过反射可以调用私有结构，比如私有的构造器、方法、属性
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class);//构造器
        constructor1.setAccessible(true);
        Object p1 = constructor1.newInstance("Jerry");
        System.out.println(p1);

        Field name = clazz.getDeclaredField("name");//属性
        name.setAccessible(true);
        name.set(p1, "A");
        System.out.println(p1);

        Method showString = clazz.getDeclaredMethod("reverse", String.class);//方法
        showString.setAccessible(true);
        Object ret = showString.invoke(p1, "string");
        System.out.println("可以获得返回值：" + ret);
    }

    //反射机制与面向对象的封装性看上去很矛盾，如何看待这两种技术？

    @Test
    public void test3() throws ClassNotFoundException {
        //获取Class的实例的方式一
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二  通过运行时类的对象
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);
        //方式三  调用Class的静态方法forName(String classPath)
        Class clazz3 = Class.forName("com.yinziming.part15.reflect.Person");
        System.out.println(clazz3);

        //方式四  使用类的加载器ClassLoader（了解）
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.yinziming.part15.reflect.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);
        System.out.println(clazz1 == clazz4);
    }

    @Test
    public void test4() {
        //Class实例可以是哪些结构
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        System.out.println(c10 == c11);//true只要元素类型与维度一样就是同一个Class
    }
}

class Person {
    private String name;
    public int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public void show() {
        System.out.println("public show()");
    }

    private String reverse(String s) {
        System.out.println("private reverse() and has a parameter \"" + s + "\"");
        return new StringBuffer(s).reverse().toString();
    }
}