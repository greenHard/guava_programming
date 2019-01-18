package com.zhang.guava.others;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Hashing 算法
 *
 * {@link Hashing}
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-18 14:47
 * @since 1.0
 */
public class HashingExample {
    public static void main(String[] args) {
        // sha256 ...
        HashFunction sha256 = Hashing.sha256();
        int length = sha256.bits();
        System.out.println(length);
        HashCode hashCode = sha256.hashBytes("hello".getBytes());
        System.out.println(hashCode.toString());
    }
}
