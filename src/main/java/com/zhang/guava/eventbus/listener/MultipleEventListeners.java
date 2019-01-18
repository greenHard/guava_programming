package com.zhang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多事件监听器
 * @author yuyang.zhang
 * @date 2019/1/16
 */
public class MultipleEventListeners {

    private final static Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

    @Subscribe
    public void task1(String event) {
        LOGGER.info("the event [{}] received and will take a action by ==task1== ", event);
    }

    @Subscribe
    public void task2(String event) {
        LOGGER.info("the event [{}] received and will take a action by ==task2== ", event);
    }

    @Subscribe
    public void intTask(Integer event) {
        LOGGER.info("the event [{}] received and will take a action by ==intTask== ", event);
    }
}
