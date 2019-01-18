package com.zhang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;

/**
 * 简单监听器
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-16 10:58
 * @since 1.0
 */
public class SimpleListener {

    @Subscribe
    public void doAction(final String event) {
        System.out.println("doAction Received event [" + event + "] and will take a action");
    }

    @Subscribe
    public void doAction2(final String event) {
        System.out.println("doAction2 Received event [" + event + "] and will take a action");
    }
}
