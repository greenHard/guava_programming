package com.zhang.guava.utilites;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link CharMatcher}
 * @author <p>yuyang.zhang<p>
 * @date 2018-11-08 19:32
 * @since 1.0
 */
public class CharMatcherTest {

    @Test
    public void testRemoveWhiteSpace(){
        String tabsAndSpaces = "String  with      spaces    and tabs";
        String expected = "String with spaces and tabs";
        String result = CharMatcher.whitespace().collapseFrom(tabsAndSpaces, ' ');
        assertThat(result,equalTo(expected));
}

    @Test
    public void testTrimRemoveWhiteSpace(){
        String tabsAndSpaces = "         String  with      spaces    and tabs     ";
        String expected = "String with spaces and tabs";
        String result = CharMatcher.whitespace().trimAndCollapseFrom(tabsAndSpaces, ' ');
        assertThat(result,equalTo(expected));
    }

    @Test
    public void testRetainFrom(){
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";
        String retained = CharMatcher.digit().retainFrom(lettersAndNumbers);
        assertThat(retained,equalTo(expected));
    }

    @Test
    public void testRemoveFrom(){
        String result = CharMatcher.is('a').removeFrom("bazaar");
        String expected = "bzr";
        assertThat(result,equalTo(expected));
    }

}
