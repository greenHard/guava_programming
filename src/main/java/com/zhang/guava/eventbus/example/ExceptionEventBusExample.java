package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.EventBus;
import com.zhang.guava.eventbus.listener.ExceptionListener;

/**
 * 异常事件处理
 * <p>
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-16 11:26
 * @since 1.0
 */
public class ExceptionEventBusExample {
    public static void main(String[] args) {
        // 事件总线
        final EventBus eventBus = new EventBus((exception, context) -> {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        });
        eventBus.register(new ExceptionListener());
        eventBus.post("exception post");
    }
}
