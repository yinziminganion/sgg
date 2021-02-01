package com.yinziming.part03.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * |----Collection接口
 * |----Set接口：存储无序的、不可重复的数据，即集合
 * |----HashSet：Set的主要实现类，线程不安全，可以存储null
 * |----LinkedHashSet：HashSet的子类，遍历其内部数据时，可以按照添加的顺序遍历
 * |----TreeSet：底层是红黑树；可以按照添加对象的指定属性进行排序
 * <p>
 * 1. Set中没有额外定义过新的方法，使用的都是Collection中声明过的方法
 */
public class SetTest {
    /**
     * 无序性：不等于随机性，存储的数据在底层数组中，并非按照数组索引的顺序添加(LinkedHashSet除外 )，而是根据数据的哈希值决定的
     * 不可重复性：相同的元素只能添加一个
     * <p>
     * 添加元素过程：以HashSet为例，底层数组+链表
     * 向HashSet中添加元素a，首先调用元素a所在类的HashCode方法，计算元素a的哈希值
     * 此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置也即索引位置，判断数组此位置上是否已经有元素
     * 如果此位置上没有其他元素则元素a添加成功
     * 如果此位置上有其他元素b或以链表方式存在的多个元素，则比较元素a和元素b的hash值
     * 如果hash不相同则添加a
     * 如果hash相同则调用元素a所在类的equals方法，若返回true则不添加，否则添加
     * jdk7中，元素a放到数组中，指向原来的元素
     * jdk8中，原来的元素在数组中，指向元素a
     */
    @Test
    public void test1() {
        HashSet set = new HashSet();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("CC");
        set.add(new Person("tom", 20));
        set.add(123);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    /**
     * LinkedHashSet作为HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个数据和后一个数据
     * 有点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet
     */
    @Test
    public void test2() {
        HashSet set = new LinkedHashSet();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("CC");
        set.add(new Person("tom", 20));
        set.add(123);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    /**
     * 向TreeSet中添加的数据，必须是相同类型的
     * 两种排序方式：自然排序(Comparable)和定制排序(Comparator)
     * 自然排序中比较两个对象是否相同的标准为compareTo方法返回0，而不是equals方法
     */
    @Test
    public void test3() {
        TreeSet set = new TreeSet();
        set.add(123);
        set.add(-123);
        set.add(456);
        set.add(-456);
//        set.add("AA");
//        set.add("CC");
//        set.add(new Person("tom", 20));
        set.add(123);
        set.add(456);
        for (Object o : set) {
            System.out.println(o);
        }
    }
}
