package com.yinziming.part03.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorTest {
    @Test
    public void test1() {
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add(1233);
        c1.add(1234);
        c1.add(1235);
        c1.add(1236);
        Iterator iterator = c1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2() {
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add(1233);
        c1.add(1234);
        c1.add(1235);
        c1.add(1236);
        Iterator iterator = c1.iterator();
        //下面这种是错误的
//        while(iterator.next() != null){
//            System.out.println(iterator.next());
//        }
    }

    @Test
    public void test3() {
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add(1233);
        c1.add(1234);
        c1.add(1235);
        c1.add(1236);
        c1.add(new String("Tom"));
        Iterator iterator = c1.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if ("Tom".equals(obj)) {
                iterator.remove();//删除迭代器指向的元素
                //如果还未调用next()或者在上一次调用next方法之后已经调用了remove方法，再调用remove方法会报IllegalStateException
            }
        }
        Iterator iterator1 = c1.iterator();
        while(iterator1.hasNext()) System.out.println(iterator1.next());
    }
}
