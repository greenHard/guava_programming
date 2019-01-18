package com.zhang.guava.utilites;

import com.google.common.base.Strings;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link Strings}
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-13 11:13
 * @since 1.0
 */
public class StringsTest {

    @Test
    public void testNullToEmpty(){
        String result = Strings.nullToEmpty(null);
        assertThat(result,equalTo(""));
    }

    @Test
    public void testEmptyToNull(){
        String result = Strings.emptyToNull("");
        assertThat(result, equalTo(null));
    }

    @Test
    public void testIsNullOrEmpty(){
        boolean result = Strings.isNullOrEmpty("");
        assertThat(result, equalTo(true));
    }

    @Test
    public void testPadStart(){
        String str1 = Strings.padStart("7", 3, '0');
        String str2 = Strings.padStart("2010", 3, '0');
        assertThat(str1, equalTo("007"));
        assertThat(str2, equalTo("2010"));
    }

    @Test
    public void testPadEnd(){
        String str1 = Strings.padEnd("4.", 5, '0');
        String str2 = Strings.padEnd("2010", 3, '!');
        assertThat(str1, equalTo("4.000"));
        assertThat(str2, equalTo("2010"));
    }

    @Test
    public void testRepeat(){
        String result = Strings.repeat("guava", 3);
        assertThat(result, equalTo("guavaguavaguava"));
    }

    /**
     *  a.toString().startsWith(prefix) && b.toString().startsWith(prefix)}
     */
    @Test
    public void testCommonPrefix() {
        String result = Strings.commonPrefix("Java", "Guava");
        assertThat(result, equalTo(""));
        result = Strings.commonPrefix("Java", "Jenkins");
        assertThat(result, equalTo("J"));
    }

    @Test
    public void testCommonSuffix() {
        String result = Strings.commonSuffix("Java", "Guava");
        assertThat(result, equalTo("ava"));
        result = Strings.commonSuffix("Java", "Jenkins");
        assertThat(result, equalTo(""));
    }





}
