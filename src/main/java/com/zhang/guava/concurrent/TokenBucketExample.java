package com.zhang.guava.concurrent;

/**
 * 测试
 *
 * @author yuyang.zhang
 * @date 2019/1/15
 */
public class TokenBucketExample {
    public static void main(String[] args) {
        final TokenBucket tokenBucket = new TokenBucket();
        for (int i = 0; i < 200; i++) {
            new Thread(tokenBucket::buy).start();
        }
    }
}
