package com.zhang.guava.utilites;

import com.google.common.base.Preconditions;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * unit test for {@link Preconditions}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2018-10-24 10:55
 * @since 1.0
 */
public class PreconditionsTest {


    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument() {
        Preconditions.checkArgument(false);

    }

    @Test
    public void testCheckArgumentWithErrorMessageTemplate() {
        try {
            Preconditions.checkArgument(false, "error %s", 1);
        } catch (IllegalArgumentException e) {
            assertThat("error 1", equalTo(e.getMessage()));
        }
    }


    // template = String.valueOf(template); // null -> "null"
    @Test
    public void testCheckElementIndex() {
        try {
            Preconditions.checkElementIndex(2, 1, "abc");
        } catch (IndexOutOfBoundsException e) {
            assertThat("abc (2) must be less than size (1)", equalTo(e.getMessage()));
        }
    }

    @Test
    public void testCheckNotNull() {
        try {
            Preconditions.checkNotNull(null, "the %s is null", "string");
        } catch (NullPointerException e) {
            assertThat("the string is null", equalTo(e.getMessage()));
        }
    }

    @Test
    public void testCheckPositionIndex(){
        try {
            Preconditions.checkElementIndex(2, 1, "abc");
        } catch (IndexOutOfBoundsException e) {
            assertThat("abc (2) must be less than size (1)", equalTo(e.getMessage()));
        }
    }

    @Test
    public void testCheckPositionIndexs(){
        try{
            Preconditions.checkPositionIndexes(2, 5, 3);
        } catch (IndexOutOfBoundsException e){
            assertThat("end index (5) must not be greater than size (3)", equalTo(e.getMessage()));
        }
    }


    @Test
    public void testCheckState(){
        try{
            Preconditions.checkState(false, "check state %s", "false");
        }catch (IllegalStateException e){
            assertThat("check state false",equalTo(e.getMessage()));
        }

    }

    /**
     *  源码分析 核心方法
     *  static String format(String template, @Nullable Object... args) {
     *         // 将null 转化成"null"
     *         template = String.valueOf(template); // null -> "null"
     *
     *         // start substituting the arguments into the '%s' placeholders
     *         StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
     *         int templateStart = 0;
     *         int i = 0;
     *         while (i < args.length) {
     *             int placeholderStart = template.indexOf("%s", templateStart);
     *             if (placeholderStart == -1) {
     *                 break;
     *             }
     *             builder.append(template, templateStart, placeholderStart);
     *             builder.append(args[i++]);
     *             templateStart = placeholderStart + 2;
     *         }
     *         builder.append(template, templateStart, template.length());
     *
     *         // 如果模板没有%s,则在后面拼接参数,或者参数超过了模板中的%s,在后面拼接[arg]
     *         // if we run out of placeholders, append the extra args in square braces
     *         if (i < args.length) {
     *             builder.append(" [");
     *             builder.append(args[i++]);
     *             while (i < args.length) {
     *                 builder.append(", ");
     *                 builder.append(args[i++]);
     *             }
     *             builder.append(']');
     *         }
     */





}
