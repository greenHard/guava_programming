package com.zhang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 具体的监听器
 * @author yuyang.zhang 
 * @date 2019/1/16 
 */ 
public class ConcreteListener extends BaseListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteListener.class);

    @Subscribe
    public void conTask(String event) {
        LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "conTask");
    }
}
