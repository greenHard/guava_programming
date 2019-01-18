package com.zhang.guava.io;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-17 16:43
 * @since 1.0
 */
public class FileTest {

    private final String SOURCE_FILE = "D:\\study\\code\\guava_programming\\src\\test\\resources\\io\\source.txt";

    private final String TARGET_FILE = "D:\\study\\code\\guava_programming\\src\\test\\resources\\io\\target.txt";

    private final String SOURCE_DIR = "D:\\study\\code\\guava_programming\\src\\test\\resources";


    @Test
    public void testCopyFileWithGuava() throws IOException {
        File targetFile = new File(TARGET_FILE);
        File sourceFile = new File(SOURCE_FILE);
        Files.copy(sourceFile, targetFile);
        assertThat(targetFile.exists(), equalTo(true));
        HashCode sourceHashCode = Files.asByteSource(sourceFile).hash(Hashing.sha256());
        HashCode targetHashCode = Files.asByteSource(targetFile).hash(Hashing.sha256());
        assertThat(sourceHashCode.toString(), equalTo(targetHashCode.toString()));
    }

    // 复制文件使用NIO2
    @Test
    public void testCopyFileWithJDKNio2() throws IOException {
        // 复制文件
        java.nio.file.Files.copy(
                Paths.get(SOURCE_DIR, "io", "source.txt"),
                Paths.get(SOURCE_DIR, "io", "target.txt"),
                StandardCopyOption.REPLACE_EXISTING
        );
        File targetFile = new File(TARGET_FILE);
        assertThat(targetFile.exists(), equalTo(true));
    }

    // 移动文件
    @Test
    public void testMoveFile() throws IOException {
        try {
            Files.move(new File(SOURCE_FILE), new File(TARGET_FILE));
            assertThat(new File(TARGET_FILE).exists(), equalTo(true));
            assertThat(new File(SOURCE_FILE).exists(), equalTo(false));
        } finally {
            Files.move(new File(TARGET_FILE), new File(SOURCE_FILE));
        }
    }


    @Test
    public void testToString() throws IOException {
        final String expectedString = "today we will share the guava io knowledge.\n" +
                "but only for the basic usage. if you wanted to get the more details information\n" +
                "please read the guava document or source code.\n" +
                "\n" +
                "The guava source code is very cleanly and nice.";
        List<String> strings = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String result = Joiner.on("\n").join(strings);
        assertThat(result, equalTo(expectedString));
    }


    // 统计每行单词的字数
    @Test
    public void testToProcessString() throws IOException {
        LineProcessor<List<Integer>> lineProcessor = new LineProcessor<List<Integer>>() {
            private final List<Integer> lengthList = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
                if (line.length() == 0) return false;
                lengthList.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lengthList;
            }
        };
        List<Integer> result = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8).readLines(lineProcessor);
        System.out.println(result);
    }

    // 文件 hash
    @Test
    public void testFileSha() throws IOException {
        File file = new File(SOURCE_FILE);
        HashCode hashCode = Files.asByteSource(file).hash(Hashing.sha256());
        System.out.println(hashCode);
    }

    // 写文件,默认覆盖
    @Test
    public void testFileWrite() throws IOException {
        String testPath = "D:\\study\\code\\guava_programming\\src\\test\\resources\\io\\testFileWrite.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();
        String content1 = "content 1";
        Files.asCharSink(testFile, Charsets.UTF_8).write(content1);
        String actully = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actully, equalTo(content1));
        String content2 = "content 2";
        Files.asCharSink(testFile, Charsets.UTF_8).write(content2);
        actully = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actully, equalTo(content2));
    }

    // 追加模式
    @Test
    public void testFileAppend() throws IOException {
        String testPath = "D:\\study\\code\\guava_programming\\src\\test\\resources\\io\\testFileWrite.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();
        CharSink charSink = Files.asCharSink(testFile, Charsets.UTF_8, FileWriteMode.APPEND);
        charSink.write("content1");
        String actullay = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actullay, equalTo("content1"));
        charSink.write("content2");
        actullay = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actullay, equalTo("content1content2"));
    }
}
