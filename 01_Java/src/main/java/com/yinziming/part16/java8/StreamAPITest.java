package com.yinziming.part16.java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest {
    @Test
    public void test1() {
        //通过集合或数组创建Stream
        List<Integer> ints1 = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = ints1.stream();//顺序流
        Stream<Integer> integerStream = ints1.parallelStream();//并行流

        Integer[] ints2 = {5, 6, 7, 8, 9};
        Stream<Integer> stream1 = Arrays.stream(ints2);

        //Stream的of()
        Stream<Integer> integerStream1 = Stream.of(2, 3, 6, 8);

        //创建无限流（了解）
        //迭代
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);//输出前10个偶数
        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);//生成10个随机小数
    }

    @Test
    public void test2() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> stream = integers.stream();//只能用一次，再用需要再生成一个stream

        //筛选类
        stream.filter(n -> n % 2 == 0).forEach(System.out::println);
        integers.stream().skip(3).forEach(System.out::println);
        integers.stream().distinct().forEach(System.out::println);//去重

        //映射类
        List<Character> characters = Arrays.asList('a', 'b', 'c');
        characters.stream().map((Character c) -> c.toString().toUpperCase()).forEach(System.out::println);

        List<String> list = Arrays.asList("abc", "def");/*flatMap(Function f)接收一个函数作为参数，将流中的每个值都换成另一个流*/
        Stream<Character> characterStream = list.stream().flatMap((String s) -> {/*然后把所有流连成一个流*/
            ArrayList<Character> chars = new ArrayList<>();
            for (var c : s.toCharArray()) {
                chars.add(c);
            }
            return chars.stream();
        });
        characterStream.forEach(System.out::println);


    }

    @Test
    public void test3() {
        Object[] array = Stream.generate(Math::random).limit(5).toArray();
        for (Object o : array) {
            System.out.print(o + " ");
        }
        System.out.println();

        //排序
        Arrays.stream(array).sorted().forEach(System.out::println);//sorted自然排序
        Arrays.stream(array).sorted(((o1, o2) -> -Double.compare((Double) o1, (Double) o2))).forEach(System.out::println);//定制排序
    }

    /**
     * allMatch(Predicate p)检查是否匹配所有元素
     * anyMatch(Predicate p)检查是否至少匹配一个元素
     * noneMatch(Predicate p)检查是否没有匹配的元素
     * findFirst返回第一个元素
     * findAny返回当前流中的任意元素
     * count返回流中元素的总个数
     * max(Comparator c)返回流中的最大值
     * forEach(Consumer c)内部迭代
     */
    @Test
    public void test4() {

    }

    /**
     * reduce(T identity, BinaryOperator)将流中元素反复结合起来得到一个值，返回T
     */
    @Test
    public void test5() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    /**
     * collect(Collector c)将流转换成其他形式，接受一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test6() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect = list.stream().filter(e -> e > 5).collect(Collectors.toList());//导出成List
//        Set<Integer> set = list.stream().filter(e -> e > 5).collect(Collectors.toSet());//导出成Set
        collect.forEach(System.out::println);
    }
}
