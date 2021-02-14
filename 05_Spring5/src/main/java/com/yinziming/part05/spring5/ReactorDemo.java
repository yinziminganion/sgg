package com.yinziming.part05.spring5;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Stream;

public class ReactorDemo {
    public static void main(String[] args) {
        //just方法可以直接声明
        Flux.just(1, 2, 3, 4).subscribe(System.out::println);
        Mono.just(1).subscribe(System.out::println);

        //其他方法
        Flux.fromArray(new Integer[]{1, 2, 3});
        Flux.fromIterable(Arrays.asList(1, 2, 3));
        Flux.fromStream(Stream.of(1, 2, 3));
    }
}
