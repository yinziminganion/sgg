package com.yinziming.part03.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ForEachTest {
    @Test
    public void test1() {
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add(1233);
        c1.add(1234);
        c1.add(1235);
        c1.add(1236);
        for (Object i :
                c1) {//底层仍然是迭代器
            System.out.println(i);
        }
    }
    @Test
    public  void test2(){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i : arr) System.out.println(i);

    }
    @Test
    public void test3(){
        String[] strings = {"MM", "MM", "MM"};
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "GG";

        }
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);//GG
        }
    }
    @Test
    public void test4(){
        String[] strings = {"MM", "MM", "MM"};
        for (var i : strings) {
            i = "GG";

        }
        for (String string : strings) {
            System.out.println(string);//MM
        }
    }
}
