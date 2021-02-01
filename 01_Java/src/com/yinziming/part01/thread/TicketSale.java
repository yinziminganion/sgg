package com.yinziming.part01.thread;

/**
 * 线程的安全问题
 * 在一个线程操作ticket的时候，其他线程不能操作（同步机制）
 * 方式一：同步代码块，操作共享数据的代码就是需要被同步的代码
 * synchronized(同步监视器){需要被同步的代码}
 * 方式二：同步方法
 * public synchronized void run(){}
 */
class Ticket implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(true){
            synchronized (Ticket.class){
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ' ' + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
public class TicketSale {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
    }
}
