package com.yinziming.part11.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionTest {
    @Test
    public void test1() {
        Collection c1 = new ArrayList();
        c1.add("AA");
        c1.add("BB");
        c1.add(123);

        System.out.println(c1.size());

        Collection c2 = new ArrayList();
        ;
        c2.addAll(c1);

        c1.clear();

        System.out.println(c1.isEmpty());
    }

    @Test
    public void test2() {
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add(456);
        c1.add(new String("Tom"));
        c1.add(false);
        c1.add(246);
        c1.add(new Person("Jerry", 20));

        System.out.println(c1.contains(123));//true
        System.out.println(c1.contains(new Person("Jerry", 20)));//false
    }

    @Test
    public void test3() {
        Collection c = new ArrayList();
        c.add(123);
        c.add(456);
        System.out.println(c.size());
        c.remove(123);
        System.out.println(c.size());
    }

    @Test
    public void test4() {
        Collection c1 = Arrays.asList(123, 456, 7789);
        Collection c2 = Arrays.asList(123, 456, 7789);
        System.out.println(c1.equals(c2));
        Object[] objects = c1.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }

        List<String> strings = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(strings);

        List ints = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(ints);


    }

    @Test
    public void test5() {

    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}