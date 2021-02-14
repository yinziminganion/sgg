package com.yinziming.part05.spring5;

import java.util.concurrent.Flow;

public class FlowDemo {
    public static void main(String[] args) {
        Flow.Publisher<String> publisher = subscriber -> {
            subscriber.onNext("1");
            subscriber.onNext("2");
            subscriber.onError(new RuntimeException("出错"));
        };
        publisher.subscribe(new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.cancel();
            }

            @Override
            public void onNext(String item) {
                System.out.println("item = " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("出错了");
            }

            @Override
            public void onComplete() {
                System.out.println("publish completed");
            }
        });
    }
}
