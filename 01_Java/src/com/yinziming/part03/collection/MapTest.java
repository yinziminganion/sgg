package com.yinziming.part03.collection;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 *      |----Map 双列数据，存储key-value对的数据
 *          |----HashMap 作为Map的主要实现类，线程不安全，key和value可以为null
 *              |----LinkedHashMap 遍历时可以按照添加的顺序
 *          |----TreeMap 保证按照添加的key-value对进行排序，实现排序遍历，此时考虑key的自然排序或定制排序，底层红黑树
 *          |----HashTable 作为Map的古老实现类，线程安全，key和value不能null
 *              |----Properties 常用来处理配置文件，key和value都是String
 *
 *      HashMap底层：jdk7之前数组+链表，jdk8又增加红黑树
 *
 *
 *      Map结构的理解：
 *          key: 无序的、不可重复的，使用Set存储所有的key，key所在类要重写equals和hashcode方法
 *          value: 无序的、可重复的，使用Collection存储所有的value，value所在类要重写equals
 *          一个键值对key-value就构成了一个Entry对象
 *
 *      HashMap底层实现
 *          jdk7中
 *              new HashMap()底层创建了长度是16的一维数组Entry[] table
 *              map.put(k, v)的过程：
 *                  首先调用k所在类的hashcode()计算k的哈希值，如果此哈希值对应位置为空则k-v添加成功
 *                  如果此位置上不为空，意味着此位置上存在一个或多个数据以链表的方式存在，则需要比较k与该位置上数据的哈希值
 *                  若k与该位置其他数据的哈希值都不同则添加k-v，若哈希值与已经存在的一个数据的相同则继续比较k所在类的equals方法
 *                  如果equals返回false则添加成功，否则使用v替换k本来映射的value值
 *              扩容方式维扩容为原来的两倍，并复制原来的内容到新的
 *          jdk8中
 *              new HashMap()没有创建长度16的数组，底层的单元是Node而不是Entry，首次调用put方法时才创建长度为16的数组
 *              jdk7中底层结构只有数组+链表，jdk8中多了红黑树
 *              当数组某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64 时，此时此索引位置上的所有数据改用红黑树存储
 *
 *      LinkedHashMap底层实现（了解）
 *          Entry before, after;能够记录添加的元素的先后顺序
 *
 *
 *      Map主要方法
 *          增删改：
 *              Object put(Object key, Object value)添加或修改key-value到当前map对象中
 *              void putAll(Map m)将m中的key-value添加到当前map
 *              Object remove(Object key)移除指定的key-value，并返回value
 *              void clear()清空当前map
 *          查：
 *              Object get(Object key)获取key对应的value
 *              boolean containsKey(Object key)是否包含指定的key
 *              boolean containsValue(Object value)是否包含指定的value
 *              int size()返回map中key-value的个数
 *              boolean isEmpty()返回当前map是否为空
 *              boolean equals(Object obj)判断当前map和参数对象obj是否相等
 *          元视图操作的方法：
 *              Set keySet()返回所有key构成的集合
 *              Collection values()返回所有value构成的Collection
 *              Set entrySet()返回所有key-value对构成的Set集合
 *
 */
public class MapTest {
    @Test
    public void treeMapTest1(){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("AA", 18);
        map.put("BB", 20);
        map.put("CC", 22);
        map.put("DD", 24);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -----> " + entry.getValue());
        }
    }
    @Test
    public void testProperties(){
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("test.properties")) {
            properties.load(fis);
            System.out.println(properties.get("name"));
            System.out.println(properties.get("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
