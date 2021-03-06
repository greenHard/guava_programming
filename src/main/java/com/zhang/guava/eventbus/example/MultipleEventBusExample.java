package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.EventBus;
import com.zhang.guava.eventbus.listener.MultipleEventListeners;

/**
 * 多事件举例
 * @author yuyang.zhang
 * @date 2019/1/16
 */
public class MultipleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event");
        eventBus.post("I am string event");
        System.out.println("post the int event");
        eventBus.post(1000);
    }
}
