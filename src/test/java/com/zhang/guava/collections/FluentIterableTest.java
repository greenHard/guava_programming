package com.zhang.guava.collections;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * {@link FluentIterable}
 * 和Java的{@link Stream}类似
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-13 15:00
 * @since 1.0
 */
public class FluentIterableTest {

    private FluentIterable<String> build() {
        ArrayList<String> list = Lists.newArrayList("Zhang", "Bill", "Guava","Java");
        return FluentIterable.from(list);
}

    @Test
    public void testFilter() {
        FluentIterable<String> fit = build();
        assertThat(fit.size(), equalTo(4));
        FluentIterable<String> result = fit.filter(e -> e != null && e.length() > 4);
        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void testAppend() {
        FluentIterable<String> fit = build();
        ArrayList<String> append = Lists.newArrayList("APPEND");
        assertThat(fit.size(), equalTo(4));
        FluentIterable<String> appendFI = fit.append(append);
        assertThat(appendFI.size(), equalTo(5));
        assertThat(appendFI.contains("APPEND"), is(true));

        appendFI = appendFI.append("APPEND2");
        assertThat(appendFI.size(), equalTo(6));
        assertThat(appendFI.contains("APPEND2"), is(true));
        assertThat(appendFI.contains("Bill"), is(true));
    }

    @Test
    public void testMatch() {
        FluentIterable<String> fit = build();
        boolean result = fit.allMatch(e -> e != null && e.length() >= 4);
        assertThat(result, is(true));

        result = fit.anyMatch(e -> e != null && e.length() == 5);
        assertThat(result, is(true));

        Optional<String> optional = fit.firstMatch(e -> e != null && e.length() == 5);
        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), equalTo("Zhang"));
    }

    @Test
    public void testFirst$Last() {
        FluentIterable<String> fit = build();
        Optional<String> optional = fit.first();
        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), equalTo("Zhang"));

        optional = fit.last();
        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), equalTo("Java"));
    }

    @Test
    public void testLimit() {
        FluentIterable<String> fit = build();
        FluentIterable<String> limit = fit.limit(3);
        System.out.println(limit);
        assertThat(limit.contains("Java"), is(false));
        limit = fit.limit(300);
        System.out.println(limit);
        assertThat(limit.contains("Java"), is(true));

    }

    /**
     * DSL
     */
    @Test
    public void testCopyIn() {
        FluentIterable<String> fit = build();
        ArrayList<String> list = Lists.newArrayList("Copy");
        ArrayList<String> result = fit.copyInto(list);
        assertThat(result.size(), equalTo(5));
        assertThat(result.contains("Bill"), is(true));
    }

    @Test
    public void testCycle() {
        FluentIterable<String> fit = build();
        FluentIterable<String> cycle = fit.cycle().limit(20);
        cycle.forEach(System.out::println);
    }

    // map
    @Test
    public void testTransform() {
        FluentIterable<String> fit = build();
        fit.transform(e -> e.length()).forEach(System.out::println);
    }

    // flapMap
    @Test
    public void testTransformAndConcat() {
        FluentIterable<String> fit = build();
        List<Integer> list = Lists.newArrayList(1);
        FluentIterable<Integer> result = fit.transformAndConcat(e -> list);
        result.forEach(System.out::println);
    }

    /**
     * A----->API---->B(Server)
     * flatMap
     */
    @Test
    public void testTransformAndConcatInAction() {
        ArrayList<Integer> cTypes = Lists.newArrayList(1, 2);
        FluentIterable.from(cTypes).transformAndConcat(this::search)
                .forEach(System.out::println);
    }

    @Test
    public void testJoin() {
        FluentIterable<String> fit = build();
        String result = fit.join(Joiner.on(','));
        assertThat(result, equalTo("Zhang,Bill,Guava,Java"));
    }


    private List<Customer> search(int type) {
        if (type == 1) {
            return Lists.newArrayList(new Customer(type, "Bill"));
        } else {
            return Lists.newArrayList(new Customer(type, "Zhang"), new Customer(type, "Yu"), new Customer(type, "Yang"));
        }
    }

    class Customer {
        final int type;
        final String name;

        Customer(int type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
