package com.zhang.guava.utilites;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * unit test for {@link Ordering}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-10-24 15:25
 * @since 1.0
 */
public class OrderingTest {

    private List<Integer> numbers = Arrays.asList(2, 5, 51, 53, 32, 43, 16);


    private List<Integer> numbersWithNull = Arrays.asList(2, 5, 51, 53, 32, 43, 16, null);

    private Ordering<Comparable> ordering = Ordering.natural();

    @Test
    public void testCreateOrdering() {
        // natural
        Ordering<Integer> ordering = Ordering.natural();
        int compare = ordering.compare(1, 2);
        assertThat(-1, equalTo(compare));

        // usingToString
        // 比较对象的toString方法比较
        Ordering<Object> ordering2 = Ordering.usingToString();
        int compare1 = ordering2.compare(new Example("zhangsan"), new Example("lisi"));
        assertThat(0, equalTo(compare1));

        // new
        Ordering<String> ordering3 = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        int compare3 = ordering3.compare("hello", "world");
        assertThat(0, equalTo(compare3));

        // 使用现有的comparator
        Ordering<Example> ordering4 = Ordering.from(new Example("zhang"));
        int compare4 = ordering4.compare(new Example("bill"), new Example("zhang"));
        assertThat(-1, equalTo(compare4));
    }


    @Test
    public void testNullFirstAndLast(){

    }


    @Test
    public void testMaxAndMin() {
        // max
        Integer max = ordering.max(numbers);
        assertThat(53, equalTo(max));

        // min
        Integer min = ordering.min(numbers);
        assertThat(2, equalTo(min));
    }

    @Test
    public void testIsOrdered() {
        boolean ordered = ordering.isOrdered(numbers);
        assertThat(false, equalTo(ordered));

        boolean strictlyOrdered = ordering.isStrictlyOrdered(numbers);
        assertThat(false, equalTo(strictlyOrdered));

    }

    @Test
    public void testSortedCopy(){
        assertThat("[2, 5, 51, 53, 32, 43, 16]",equalTo(numbers.toString()));

        // 拷贝复制之后的集合
        List<Integer> integers = ordering.sortedCopy(numbers);
        assertThat("[2, 5, 16, 32, 43, 51, 53]", equalTo(integers.toString()));

        // 不可变的集合
        ImmutableList<Integer> immutableIntegers = ordering.immutableSortedCopy(numbers);
        assertThat("[2, 5, 16, 32, 43, 51, 53]",equalTo(immutableIntegers.toString()));
    }


    @Test
    public void testGreatestAndLeast(){
        // 从大到小排序,取2个数
        List<Integer> greatestList = ordering.greatestOf(numbers, 2);
        assertThat("[53, 51]",equalTo(greatestList.toString()));


        List<Integer> leastList = ordering.leastOf(numbers, 2);
        assertThat("[2, 5]",equalTo(leastList.toString()));
    }





    private class Example implements Comparator<Example> {

        private String name;

        Example(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "1";
        }

        @Override
        public int compare(Example o1, Example o2) {
            return Ints.compare(o1.name.length(), o2.name.length());
        }
    }


}
