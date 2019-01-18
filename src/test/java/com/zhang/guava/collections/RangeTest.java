package com.zhang.guava.collections;

import com.google.common.collect.Range;
import org.junit.Test;

/**
 * {@link com.google.common.collect.Range}
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-14 9:25
 * @since 1.0
 */
public class RangeTest {

    @Test
    public void testRangeClosedAndOpen() {
        Range<Integer> numberRange = Range.closed(1, 10);
        System.out.println(numberRange.contains(1));
        System.out.println(numberRange.contains(10));

        numberRange = Range.open(1, 10);
        System.out.println(numberRange.contains(1));
        System.out.println(numberRange.contains(10));
    }
}
