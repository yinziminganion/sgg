package com.yinziming.part09.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock与synchronized的异同：
 * 都能解决线程安全问题
 * synchronized自动释放同步监视器，lock需要手动启动和关闭同步锁
 */
class Window implements Runnable {
    private int ticket = 100;
    final private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ' ' + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

public class ReentrantLockTest {
    public static void main(String[] args) {
        Window w = new Window();
        new Thread(w).start();
        new Thread(w).start();
        new Thread(w).start();
    }
}
