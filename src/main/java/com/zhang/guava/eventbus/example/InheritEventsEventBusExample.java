package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.EventBus;
import com.zhang.guava.eventbus.events.Apple;
import com.zhang.guava.eventbus.events.Fruit;
import com.zhang.guava.eventbus.listener.FruitEaterListener;


/**
 * 事件的继承测试
 * <p>
 * 事件也有传递性
 *
 * @author yuyang.zhang
 * @date 2019/1/16
 */
public class InheritEventsEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));
        System.out.println("============================");
        eventBus.post(new Fruit("apple"));
    }
}
