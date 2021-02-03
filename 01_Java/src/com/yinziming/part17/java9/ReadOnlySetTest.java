package com.yinziming.part17.java9;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ReadOnlySetTest {
    @Test
    public void test1() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("AA");
        strings.add("BB");
        strings.add("CC");
        List<String> list = Collections.unmodifiableList(strings);//list是一个只读的集合
        System.out.println(list);
    }

    /**
     * 集合工厂方法，快速创建只读集合
     */
    @Test
    public void test2() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> map2 = Map.ofEntries(Map.entry("Tom", 18), Map.entry("Jerry", 17));
    }
}
