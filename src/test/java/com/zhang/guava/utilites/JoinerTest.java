package com.zhang.guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * {@link Joiner}
 * @author <p>yuyang.zhang<p>
 * @date 2018-10-18 20:30
 * @since 1.0
 */
public class JoinerTest {

    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Kafka"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", null
    );

    private static final String targetFileName = "D:/upload/guava.txt";

    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        assertThat(result, equalTo("Google#Guava#Java#Kafka"));

        result = Joiner.on("#").join("Hello", "world");
        assertThat(result, equalTo("Hello#world"));
    }

    @Test
    public void testJoin_On_Join_WithNullValue_SkipNulls() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoin_On_Join_WithNullValue_SkipNulls_UseDefaultValue() {
        String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#DEFAULT"));
    }

    @Test
    public void testJoin_On_SkipNulls_Append_To_StringBuilder() {
        StringBuilder sb = new StringBuilder();
        StringBuilder builder = Joiner.on("#").skipNulls().appendTo(sb, stringListWithNullValue);
        assertThat(builder.toString(), equalTo("Google#Guava#Java"));
    }

    @Test
    public void testJoin_On_Append_To_StringBuilder() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder resultBuilder = Joiner.on("#").useForNull("DEFAULT").appendTo(builder, stringListWithNullValue);
        assertThat(resultBuilder, sameInstance(builder));
        assertThat(builder.toString(), equalTo("Google#Guava#Java#DEFAULT"));
        assertThat(resultBuilder.toString(), equalTo("Google#Guava#Java#DEFAULT"));
    }


    @Test
    public void testJoin_On_Append_To_Writer() {
        try (FileWriter writer = new FileWriter(new File(targetFileName))) {
            Joiner.on("#").useForNull("DEFAULT").appendTo(writer, stringListWithNullValue);
            assertThat(Files.isFile().test(new File(targetFileName)), equalTo(true));
        } catch (IOException e) {
            fail("append to the writer occur fetal error.");
        }
    }

    // map
    @Test
    public void testJoin_On_WithKeyValue_Separator() {
        final Map<String, String> map = new LinkedHashMap<>();
        map.put("hello", "world");
        map.put("java", "Java");
        final StringBuilder builder = new StringBuilder();
        Joiner.on("#").withKeyValueSeparator("=").appendTo(builder, map);
        assertThat(builder.toString(), equalTo("hello=world#java=Java"));
    }

    // java 8
    @Test
    public void testJoin_On_Stream() {
        String result = stringListWithNullValue.stream()
                .filter(item -> item == null || !item.isEmpty())
                .collect(joining("#"));
        assertThat(result, equalTo("Google#Guava#Java#DEFAULT"));
    }


}
