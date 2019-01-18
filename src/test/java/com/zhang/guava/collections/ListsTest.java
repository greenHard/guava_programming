package com.zhang.guava.collections;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * {@link Lists}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-13 15:49
 * @since 1.0
 */
public class ListsTest {

    //  笛卡尔积
    @Test
    public void testCartesianProduct() {
        List<List<String>> result = Lists.cartesianProduct(
                Lists.newArrayList("1", "2"),
                Lists.newArrayList("A", "B"),
                Lists.newArrayList("C", "D")
        );
        System.out.println(result);
    }

    @Test
    public void testTransform() {
        ArrayList<String> sourceList = Lists.newArrayList("Java", "Guava", "Lists");
        Lists.transform(sourceList, e -> e.toUpperCase()).forEach(System.out::println);


    }

    @Test
    public void testNewArrayListWithCapacity() {
        ArrayList<String> result = Lists.newArrayListWithCapacity(10);
        result.add("x");
        result.add("y");
        result.add("z");
        System.out.println(result);
    }

    @Test
    public void testReverse() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3");
        assertThat(Joiner.on(",").join(list), equalTo("1,2,3"));
        List<String> result = Lists.reverse(list);
        assertThat(Joiner.on(",").join(result), equalTo("3,2,1"));
    }

    @Test
    public void testPartition() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4");
        List<List<String>> result = Lists.partition(list, 2);
        System.out.println(result);
    }

}
