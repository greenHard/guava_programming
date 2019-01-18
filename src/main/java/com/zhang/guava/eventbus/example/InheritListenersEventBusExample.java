package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.EventBus;
import com.zhang.guava.eventbus.listener.ConcreteListener;

/**
 * 监听器继承的测试
 * <p>
 * 父->子都会监听到
 *
 * @author yuyang.zhang
 * @date 2019/1/16
 */
public class InheritListenersEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event");
        eventBus.post("I am string event");
        System.out.println("post the int event");
        eventBus.post(1000);
    }
}
