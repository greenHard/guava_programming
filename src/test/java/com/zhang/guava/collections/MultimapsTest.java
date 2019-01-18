package com.zhang.guava.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link com.google.common.collect.Multimaps}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-18 16:33
 * @since 1.0
 */
public class MultimapsTest {

    @Test
    public void testArrayListMultiMap() {
        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("1","Java");
        arrayListMultimap.put("1","Guava");
        arrayListMultimap.put("2", "Kafka");
        arrayListMultimap.put("2", "Kafka");
        System.out.println(arrayListMultimap);
        ArrayList<String> excepted = Lists.newArrayList("Java", "Guava");
        assertThat(excepted,equalTo(arrayListMultimap.get("1")));
        assertThat(4,equalTo(arrayListMultimap.size()));
    }

    @Test
    public void testHashMultiMap() {
        // hashTable diff key-value
        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("1","Java");
        hashMultimap.put("1","Guava");
        hashMultimap.put("2", "Kafka");
        hashMultimap.put("2", "Kafka");
        System.out.println(hashMultimap);
    }

    // ImmutableMultimap ImmutableListMultimap ImmutableMultiset TreeMultimap sorted by comparator
}
