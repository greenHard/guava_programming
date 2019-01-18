package com.zhang.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * {@link RateLimiter} 限流控制每秒的线程数
 *
 * 使用场景参考:
 * <a href="http://www.cnblogs.com/yeyinfu/p/7316972.html"><a/>
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-15 15:17
 * @since 1.0
 */
public class RateLimiterExample {
    /**
     * semaphore
     */
    private static final Semaphore semaphore = new Semaphore(4);

    /**
     * rateLimiter 控制每秒的线程数
     */
    private static final RateLimiter limiter = RateLimiter.create(0.5);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i ->
                service.submit(RateLimiterExample::testLimiter)
        );
    }

    /**
     * 测试limiter
     */
    private static void testLimiter() {
        System.out.println(currentThread() + " waiting " + limiter.acquire());
    }

    /**
     * 测试Semaphore
     */
    private static void testSemaphore() {
        try {
            semaphore.acquire();
            System.out.println(currentThread() + " is coming and do work.");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(currentThread() + " release the semaphore.");
        }
    }
}
