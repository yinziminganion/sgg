package com.yinziming.part05.spring5;

import java.util.Observable;

public class ObservableDemo extends Observable {
    public static void main(String[] args) {
        ObservableDemo observer = new ObservableDemo();
        observer.addObserver((o,arg)->System.out.println("发生变化"));
        observer.addObserver((o,arg)->System.out.println("收到被观察者通知，准备改变"));
        observer.setChanged();
        observer.notifyObservers();
    }
}
