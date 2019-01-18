package com.zhang.guava.utilites;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link Splitter}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-11-08 18:55
 * @since 1.0
 */
public class SplitterTest {

    // java
    @Test
    public void testJavaSpilt() {
        String testString = "Monday,Tuesday,,Thursday,Friday,,";
        String[] parts = testString.split(",");
        assertThat(parts.length, equalTo(5));
    }

    @Test
    public void testSplitterCharacterOn() {
        String testString = "Monday,Tuesday,,Thursday,Friday,,";
        Iterable<String> split = Splitter.on(',').split(testString);
        Iterator<String> iterator = split.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            System.out.println("第" + size + "个元素为:" + iterator.next());
            size++;
        }
        assertThat(size, equalTo(7));
    }

    @Test
    public void testSplitterCharacterOmitEmpty() {
        String testString = "Monday,Tuesday,,Thursday,Friday,,";
        Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(testString);
        Iterator<String> iterator = split.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            System.out.println("第" + size + "个元素为:" + iterator.next());
            size++;
        }
        assertThat(size, equalTo(4));

    }

    @Test
    public void testMapSplitter(){
        // 受到不兼容的更改,甚至删除，在未来的版本。
        Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");
        String startString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C","Redskins");
        testMap.put("New York City","Giants");
        testMap.put("Philadelphia","Eagles");
        testMap.put("Dallas","Cowboys");
        Map<String,String> splitMap = mapSplitter.split(startString);
        assertThat(testMap,equalTo(splitMap));

    }
}
