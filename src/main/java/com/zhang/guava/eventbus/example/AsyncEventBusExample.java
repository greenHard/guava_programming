package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.AsyncEventBus;
import com.zhang.guava.eventbus.listener.SimpleListener;

import java.util.concurrent.Executors;

/**
 * 异步EventBus
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-17 16:24
 * @since 1.0
 */
public class AsyncEventBusExample {
    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newSingleThreadExecutor());
        eventBus.register(new SimpleListener());
        eventBus.post("hello");
        System.out.println("-----end-----");
    }
}
