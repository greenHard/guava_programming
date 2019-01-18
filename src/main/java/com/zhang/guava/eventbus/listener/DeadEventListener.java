package com.zhang.guava.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * 如果EventBus发送的消息都不是订阅者关心的称之为Dead Event。
 * <p>
 * 如果没有消息订阅者监听消息， EventBus将发送DeadEvent消息，这时我们可以通过log的方式来记录这种状态
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-17 16:15
 * @since 1.0
 */
public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event) {
        System.out.println(event.getSource());
        System.out.println(event.getEvent());
    }
}
