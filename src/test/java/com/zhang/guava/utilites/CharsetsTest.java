package com.zhang.guava.utilites;

import com.google.common.base.Charsets;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * {@link com.google.common.base.Charsets}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-13 11:05
 * @since 1.0
 */
public class CharsetsTest {

    @Test
    public void testWithCharsets() {
        byte[] bytes;
        try {
            bytes = "guava".getBytes("UTF-8");
            // 这种写法有个两个问题
            // 1. 如果Java系统不支持"UTF-8"将会抛出异常
            // 2. 如果拼写错误,将会抛出异常
            bytes = "guava".getBytes(Charsets.UTF_8);

            // Java7提供了
            bytes = "guava".getBytes(StandardCharsets.UTF_8);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
