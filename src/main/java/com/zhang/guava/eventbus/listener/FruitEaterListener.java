package com.zhang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import com.zhang.guava.eventbus.events.Apple;
import com.zhang.guava.eventbus.events.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 水果监听器
 *
 * @author yuyang.zhang
 * @date 2019/1/16
 */
public class FruitEaterListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void eat(Fruit event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Fruit eat [{}].", event);
        }
    }

    @Subscribe
    public void eat(Apple event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Apple eat [{}].", event);
        }
    }
}
