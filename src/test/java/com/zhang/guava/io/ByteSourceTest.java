package com.zhang.guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
 * CharSource---->Reader
 * <p>
 * CharSink------>Writer
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-17 17:20
 * @since 1.0
 */
public class ByteSourceTest {

    private final String PATH = "D:\\study\\code\\guava_programming\\src\\test\\resources\\io\\files.PNG";

    @Test
    public void testAsByteSource() throws IOException {
        File file = new File(PATH);
        ByteSource byteSource = Files.asByteSource(file);
        byte[] readBytes = byteSource.read();
        assertThat(readBytes, is(Files.toByteArray(file)));
    }

    // slice 切割
    @Test
    public void testSliceByteSource() throws IOException {
        ByteSource byteSource = ByteSource.wrap(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09});
        ByteSource sliceByteSource = byteSource.slice(5, 5);
        byte[] bytes = sliceByteSource.read();
        for (byte b : bytes) {
            System.out.println(b);
        }
    }
}
