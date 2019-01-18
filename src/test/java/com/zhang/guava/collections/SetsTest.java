package com.zhang.guava.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * {@link Sets}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-13 16:13
 * @since 1.0
 */
public class SetsTest {

    @Test
    public void testCreate() {
        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        assertThat(set.size(), equalTo(3));
        ArrayList<Integer> list = Lists.newArrayList(1, 1, 2, 3);
        assertThat(list.size(), equalTo(4));
        HashSet<Integer> set2 = Sets.newHashSet(list);
        assertThat(set2.size(), equalTo(3));
    }

    // 笛卡尔积
    @Test
    public void testCartesianProduct() {
        Set<List<Integer>> set = Sets.cartesianProduct(
                Sets.newHashSet(1, 2),
                Sets.newHashSet(3, 4),
                Sets.newHashSet(5, 6));
        System.out.println(set);
    }

    // 组合
    @Test
    public void testCombinations() {
        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        Set<Set<Integer>> combinations = Sets.combinations(set, 2);
        combinations.forEach(System.out::println);
    }

    // 两个集合的不同
    @Test
    public void testDifference() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 4, 6);
        Sets.SetView<Integer> diffResult1 = Sets.difference(set1, set2);
        System.out.println(diffResult1);
        Sets.SetView<Integer> diffResult2 = Sets.difference(set2, set1);
        System.out.println(diffResult2);
        Sets.SetView<Integer> diffResult3 = Sets.symmetricDifference(set1, set2);
        System.out.println(diffResult3);
    }

    // 交集
    @Test
    public void testIntersection() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 4, 6);
        Sets.intersection(set1, set2).forEach(System.out::println);
    }

    // 并集
    @Test
    public void testUnionSection() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet(1, 4, 6);
        Sets.union(set1, set2).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Set<Integer> setA = Sets.newHashSet();
        for (int i = 0; i < 10000000; i++) {
            setA.add(i);
        }

        Set<Integer> setB = Sets.newHashSet();
        Random random = new Random();
        for (int i = 0; i < 10000000; i++) {
            setB.add(random.nextInt(10000000));
        }

        long start = System.currentTimeMillis();
        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println("union " + union.size());
        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println("intersection " + intersection.size());
        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println("difference " + difference.size());
        System.out.println("const time : "+ (System.currentTimeMillis()-start) + "ms");
    }
}
