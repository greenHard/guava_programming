package com.zhang.guava.eventbus.example;

import com.google.common.eventbus.EventBus;
import com.zhang.guava.eventbus.listener.DeadEventListener;

/**
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-17 16:15
 * @since 1.0
 */
public class DeadEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus("DeadEventBus") {
            @Override
            public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        eventBus.register(new DeadEventListener());
        eventBus.post("Hello");
    }
}
