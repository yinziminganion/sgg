package com.yinziming.part09.thread;

/**
 * 两种创建线程的方式对比：
 * 开发中优先选择实现Runnable接口，更适合多个线程共享变量的情况，同时继承具有单继承性的局限
 */
class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ' ' + i);
            }
        }
    }
}

public class RunnableTest {
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();

        new Thread(new MyRunnable()).start();

        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ' ' + i);
            }
        }
    }
}
