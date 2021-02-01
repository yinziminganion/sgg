package com.yinziming.part11.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Collection(interface)
 * |----List(interface)
 * |----ArrayList: 作为List接口的主要实现类，线程不安全，效率高，底层Object[]
 * |----LinkedList: 对于频繁的插入、删除操作，效率比ArrayList高，底层双向链表
 * |----Vector: List接口的古老实现类，线程安全，效率低，底层Object[]
 * 三个类都实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 * <p>
 * 1. ArrayList源码分析
 * jdk7中(类似于单例的饿汉式)
 * new ArrayList()底层创建了长度为10的Object[]数组elementData
 * 若add()方法的添加过程中容量不够了则扩容，默认扩容为原来容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中
 * 所以在已知数据规模的情况下，可以使用带参的构造器new ArrayList(int capacity)
 * <p>
 * jdk8中(类似于单例的懒汉式)
 * new ArrayList()底层elementData初始化为{}, 并没有创建长度10的数组
 * 第一次调用add()时, 才创建了长度10的数组，并将数据添加
 * 后续与jdk7没有区别
 * <p>
 * <p>
 * 2. LinkedList源码分析
 * LinkedList list = new LinkedList()内部声明了Node类型的first和last属性，默认值null
 * list.add(123);//将123封装到Node中，创建了Node对象，Node实现了双向链表的结构体
 * <p>
 * <p>
 * 3. Vector源码分析
 * 通过Vector()构造器创建对象时，底层创建长度10的数组，扩容时扩容为原来长度的两倍
 */
public class ListTest {
    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("tom", 20));
        list.add(456);
        System.out.println(list);

        //void add(int index, Object ele)在index位置插入ele元素
        list.add(1, "BB");
        System.out.println(list);

        //boolean addAll(int index, Collection elements)从index位置开始插入elements中所有元素
        List<Integer> ints = Arrays.asList(1, 2, 3);
        boolean b = list.addAll(ints);
        System.out.println(list.size());//9

        //Object get(int index)获取指定位置的元素
        System.out.println(list.get(0));

        //int indexOf(Object obj)获取obj首次出现的位置，若不存在则返回-1
        int index = list.indexOf(123);
        System.out.println(index);

        //int lastIndexOf(Object obj)返回obj在集合中末次出现的位置，若不存在则返回-1
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index)移除指定index位置的元素并返回该元素
        Object remove = list.remove(0);
        System.out.println(remove);
        System.out.println(list);

        //Object set(int index, Object ele)设置指定位置元素为ele，返回被替代的元素
        Object mm = list.set(0, "MM");
        System.out.println(mm);
        System.out.println(list);

        //List subList(int from, int to)返回从from到to位置的左闭右开区间的列表
        List list1 = list.subList(2, 4);
        System.out.println(list1);
        System.out.println(list);
    }

    @Test
    public void test2() {
        ArrayList list = new ArrayList();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(2);//index为2，也就是删除4这个元素
        System.out.println(list);
    }
}
