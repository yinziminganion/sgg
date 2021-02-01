package com.yinziming.part09.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * <p>
 * 创建多线程一共有四种方式：
 * 继承Thread类重写run方法
 * 实现Runnable接口重写run方法
 * 实现Callable接口重写call方法(有返回值)
 * 使用线程池
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService s = Executors.newFixedThreadPool(10);


        s.execute(new Number1());//Runnable
        s.submit(new Number2());//Runnable / Callable

        s.shutdown();
    }
}

class Number1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ' ' + i);
        }
    }
}

class Number2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ' ' + i);
        }
    }
}