package com.zhang.guava.io;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * 自定义的Base64 算法
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-18 10:20
 * @since 1.0
 */
public class MyBase64 {

    private final static String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private final static char[] CODE_DICT = CODE_STRING.toCharArray();

    /**
     * base64 加密
     *
     * @param plain 要加密的串
     * @return 加密之后的串
     */
    public static String encode(String plain) {
        final StringBuilder result = new StringBuilder();
        Preconditions.checkNotNull(plain);
        // 1. 将plain转成二进制
        String binaryString = toBinary(plain);
        int delta = 6 - (binaryString.length() % 6);
        for (int i = 0; i < delta && delta != 6; i++) {
            binaryString += "0";
        }
        // 2. 转义
        for (int index = 0; index < binaryString.length() / 6; index++) {
            int begin = index * 6;
            String encodeString = binaryString.substring(begin, begin + 6);
            char encodeChar = CODE_DICT[Integer.valueOf(encodeString, 2)];
            result.append(encodeChar);
        }

        if (delta != 6) {
            for (int i = 0; i < delta / 2; i++) {
                result.append("=");
            }
        }
        return result.toString();
    }


    /**
     * base64 解密
     *
     * @param encrypt 解密的串
     * @return 解密之后的数据
     */
    public static String decode(final String encrypt) {
        StringBuilder resultBuilder = new StringBuilder();
        String temp = encrypt;
        // 1.截取掉=号
        int equalIndex = temp.indexOf("=");
        if (equalIndex > 0) {
            temp = temp.substring(0, equalIndex);
        }
        // 2.将字符串转成二进制串
        String base64Binary = toBase64Binary(temp);
        // 3. 转化成字符
        for (int i = 0; i < base64Binary.length() / 8; i++) {
            int begin = i * 8;
            String str = base64Binary.substring(begin, begin + 8);
            char c = Character.toChars(Integer.valueOf(str, 2))[0];
            resultBuilder.append(c);
        }
        return resultBuilder.toString();
    }


    /**
     * 将字符串转成二进制
     *
     * @param source 字符串
     * @return 二进制
     */
    private static String toBase64Binary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            int i = CODE_STRING.indexOf(source.charAt(index));
            String charBin = Integer.toBinaryString(i);
            int delta = 6 - charBin.length();
            for (int d = 0; d < delta; d++) {
                charBin = "0" + charBin;
            }
            binaryResult.append(charBin);
        }
        return binaryResult.toString();
    }

    /**
     * 将字符串转成二进制串
     *
     * @param source 传入的字符串
     * @return 转成之后的二进制串
     */
    private static String toBinary(final String source) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            String charBin = Integer.toBinaryString(source.charAt(i));
            charBin = Strings.padStart(charBin, 8, '0');
            sb.append(charBin);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(encode("hello"));
        System.out.println(encode("a"));
        System.out.println(encode("12a"));
        System.out.println("=======================");
        System.out.println(decode("aGVsbG8="));
        System.out.println(decode("YQ=="));
        System.out.println(decode("MTJh"));
    }
}
