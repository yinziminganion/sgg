package com.yinziming.part01.thread;

/**
 * start(): 启动当前线程，调用当前线程的run()
 * run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * currentThread(): 静态方法，返回执行当前代码的线程
 * getName()、setName(): 获取、设置当前线程的名字
 * yield(): 释放当前cpu的执行权
 * join(): 在线程a中调用线程b的join(),此时线程a进入阻塞态，直到b执行完了a才会继续执行
 * stop(): 强制线程结束(Deprecated)
 * sleep(long millis): 让当前线程睡眠指定毫秒数
 * wait(): 进入阻塞状态并释放同步监视器
 * notify(): 唤醒处于wait状态中优先级最高的线程
 * notifyAll(): 唤醒所有被wait的线程
 * 注: sleep和wait的区别：
 *       (1)Thread类中声明sleep而wait是在Object类里
 *       (2)sleep可以在任何场景下调用而wait必须在同步代码块或者同步方法中使用
 *       (3)sleep不会释放同步锁，wait会
 * isAlive(): 判断当前线程是否存活
 *
 * MAX_PRIORITY = 10
 * MIN_PRIORITY = 1
 * NORM_PRIORITY = 5
 * getPriority()、setPriority(int p): 获取、设置线程的优先级
 * 优先级高只代表概率高，并不代表执行顺序先后
 */
class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ' ' + i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ' ' + i);
                    }
                    ;
                }
            }
        }.start();

        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ' ' + i);
            }

        }
    }
}
