package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.EventBus;
import com.zhang.guava.eventbus.listener.SimpleListener;

/**
 * 简单EventBus
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-16 10:57
 * @since 1.0
 */
public class SimpleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");
        System.out.println("----end----");
    }
}
