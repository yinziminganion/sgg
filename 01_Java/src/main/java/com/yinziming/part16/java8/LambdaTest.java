package com.yinziming.part16.java8;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * lambda本质是函数式接口的实例
 * 接口只能有一个抽象方法
 */
public class LambdaTest {
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("lambda Runnable");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> comparator1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = comparator1.compare(1, 2);
        System.out.println(compare1);

        Comparator<Integer> comparator2 = ((o1, o2) -> Integer.compare(o1, o2));//lambda
        int compare2 = comparator2.compare(2, 1);
        System.out.println(compare2);

        Comparator<Integer> comparator3 = Integer::compare;//方法引用
        int compare3 = comparator3.compare(1, 3);
        System.out.println(compare3);
    }

    @Test
    public void test3() {
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("consumer");

        Consumer<String> con2 = (String s) -> System.out.println(s);
        con2.accept("lambda consumer");

        Consumer<String> con3 = System.out::println;
        con3.accept("quote consumer");
    }

    @Test
    public void test4() {
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer<String> con2 = (s) -> System.out.println(s);//有了泛型所以s可以不指定是String类型
        con2.accept("lambda consumer");

    }

    @Test
    public void test5() {
        Consumer<String> con2 = s -> System.out.println(s);//一个形参可以不加小括号
        con2.accept("lambda consumer");
    }

    @Test
    public void test6() {
        Comparator<Integer> comparator1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = comparator1.compare(1, 2);
        System.out.println(compare1);

        Comparator<Integer> com2 = ((o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        });
        System.out.println(com2.compare(2, 3));
    }

    @Test
    public void test7() {
        Comparator<Integer> comparator1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = comparator1.compare(1, 2);
        System.out.println(compare1);

        Comparator<Integer> com2 = Integer::compareTo;

        System.out.println(com2.compare(2, 3));
    }

    @Test
    public void test8() {
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("consumer");

        Consumer<String> con2 = System.out::println;
        con2.accept("lambda consumer");
    }

    @Test
    public void test9() {
        Supplier<String> s = String::new;//构造器引用

        Function<Integer, String[]> func = String[]::new;//数组引用
    }
}
