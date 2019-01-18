package com.zhang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础监听器
 *
 * @author yuyang.zhang
 * @date 2019/1/16
 */
public class BaseListener extends AbstractListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseListener.class);

    @Subscribe
    public void baseTask(String event) {
        LOGGER.info("The event [{}] will be handle by {}.{}", event, this.getClass().getSimpleName(), "baseTask");
    }
}
