package com.yinziming.part15.reflect;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 */
public class NewInstanceTest {
    /**
     * 在Java bean中要求提供一个public的空参构造器的原因
     * （1）便于通过反射，创建运行时类的对象
     * （2）便于子类继承此运行时类时，默认调用super()，保证父类有此构造器
     */
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        Person p1 = clazz.newInstance();//newInstance创建对应的运行时类的对象，内部调用了运行时类提供的空参构造器（权限不能是private）
        System.out.println(p1);
    }

    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

    @Test
    public void test2() throws Exception {
        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(3);//0, 1, 2
            String path;
            switch (num) {
                case 0:
                    path = "java.util.Date";
                    break;
                case 1:
                    path = "java.lang.Object";
                    break;
                case 2:
                    path = "com.yinziming.part15.reflect.Person";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + num);
            }
            System.out.println(getInstance(path));
        }
    }
}
