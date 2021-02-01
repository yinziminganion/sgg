package com.yinziming.part12.generic;

import org.junit.jupiter.api.Test;

import java.util.*;

//G<A>, G<B>共同的父类是G<?>
public class GenericTest {
    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add("Tom");

        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
//        list.add("Tom");
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void test3() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Tom1", 27);
        map.put("Tom2", 21);
        map.put("Tom3", 24);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " -----> " + entry.getValue());
        }
    }

    @Test
    public void test4() {
        Order<Integer> order = new Order<>();
        order.setOrderE(123);

        SubOrder subOrder = new SubOrder();//子类在继承带泛型的父类时，指明了泛型类型，则子类实例化时不需要指定泛型
        subOrder.setOrderE(1122);

        SubOrder1<String> stringSubOrder1 = new SubOrder1<>();
        stringSubOrder1.setOrderE("Tom");
    }

    @Test
    public void test5() {
        Order<String> stringOrder = new Order<>();
        Integer[] integers = {1, 2, 3, 5, 6, 4};
        List<Integer> copy = stringOrder.copy(integers);
        System.out.println(copy);
    }
}

class Order<E> {
    String orderName;
    int orderID;
    E orderE;

    public Order() {
    }

    public Order(String name, int id, E orderE) {
        this.orderName = name;
        this.orderID = id;
        this.orderE = orderE;
    }

    public E getOrderE() {
        return orderE;
    }

    public static <T> List<T> copy(T[] items) { // 泛型方法是可以声明为静态的，因为泛型参数是在调用方法时确定的，并非在类的实例化时
        List<T> list = new ArrayList<>();
        Collections.addAll(list, items);
        return list;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderID=" + orderID +
                ", orderE=" + orderE +
                '}';
    }

    public void setOrderE(E orderE) {
        this.orderE = orderE;
    }


}

class SubOrder extends Order<Integer> {

}

class SubOrder1<E> extends Order<E> {

}