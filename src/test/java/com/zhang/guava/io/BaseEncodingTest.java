package com.zhang.guava.io;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Base64 测试
 * {@link BaseEncoding}
 * @author yuyang.zhang
 * @date 2019/1/17
 */
public class BaseEncodingTest {
    @Test
    public void testBase64Encode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
        System.out.println(baseEncoding.encode("a".getBytes()));
    }

    @Test
    public void testBase64Decode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(new String(baseEncoding.decode("aGVsbG8=")));
    }

    @Test
    public void testMyBase64Encode() {
        System.out.println(MyBase64.encode("hello"));
        assertThat(MyBase64.encode("hello"), equalTo(BaseEncoding.base64().encode("hello".getBytes())));
        assertThat(MyBase64.encode("scala"), equalTo(BaseEncoding.base64().encode("scala".getBytes())));
        assertThat(MyBase64.encode("kafka"), equalTo(BaseEncoding.base64().encode("kafka".getBytes())));
    }
}
