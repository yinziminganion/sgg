package com.yinziming.part01.thread;

class Clerk {
    private int count = 0;
    private final int max = 20;

    public synchronized void produceProduct() {
        if (count <= max) {
            count++;
            System.out.println(Thread.currentThread().getName() + "开始生产第" + count + "个产品");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct() {
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + "开始消费第" + count + "个产品");
            count--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {
    private Clerk clerk;

    Producer(Clerk c) {
        clerk = c;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(getName() + ": 开始消费产品。。。。。");
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread {
    private Clerk clerk;

    Consumer(Clerk c) {
        clerk = c;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(getName() + ": 开始生产产品。。。。。");
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        p1.setName("producer1");
        p1.start();
        Consumer c1 = new Consumer(clerk);
        c1.setName("consumer1");
        c1.start();
    }
}
