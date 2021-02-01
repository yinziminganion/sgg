package com.yinziming.part01.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable相比Runnable的优点
 *      call()可以有返回值，可以抛异常
 *      Callable支持泛型
 */
class NumThread implements Callable<Integer> {
    @Override
    public Integer call(){
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        NumThread t = new NumThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(t);

        new Thread(futureTask).start();

        try {
            Object sum = futureTask.get();//get()返回值即为重写之后的call的返回值
            System.out.println("结果为" + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
