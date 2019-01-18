package com.zhang.guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * {@link com.google.common.io.Closer}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-17 17:34
 * @since 1.0
 */
public class CloserTest {
    private final String PATH = "D:\\study\\code\\guava_programming\\src\\test\\resources\\io\\files.PNG";

    @Test
    public void testCloser() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File(PATH));
        Closer closer = Closer.create();
        try {
            InputStream inputStream = closer.register(byteSource.openStream());
        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }

    // Java的try-catch 存在问题,会吞掉开始的异常
    @Test(expected = RuntimeException.class)
    public void testTryCatchFinally() {
        try {
            System.out.println("work area.");
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("exception area");
            throw new RuntimeException();
        } finally {
            System.out.println("finally area");
        }
    }

    @Test
    public void testTryCatch() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            try {
                throw new RuntimeException("2");
            } catch (Exception e) {
                t.addSuppressed(e);
            }
        }
    }
}
