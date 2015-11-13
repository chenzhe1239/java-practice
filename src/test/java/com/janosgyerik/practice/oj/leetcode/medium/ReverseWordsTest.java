package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseWordsTest {
    public String reverseWords(String s) {
        String trimmed = s.trim();

        String[] parts = trimmed.split(" +");
        int partsLength = parts.length;
        if (partsLength == 0) {
            return "";
        }

        int len = trimmed.length();
        StringBuilder builder = new StringBuilder(len + 1);
        builder.append(parts[partsLength - 1]);

        for (int i = partsLength - 2; i >= 0; --i) {
            String word = parts[i];
            builder.append(" ").append(word);
        }
        return builder.toString();
    }

    @Test
    public void test_space() {
        assertEquals("", reverseWords(" "));
        assertEquals("", reverseWords("    "));
    }

    @Test
    public void test_empty() {
        assertEquals("", reverseWords(""));
    }

    @Test
    public void test_hi() {
        assertEquals("hi!", reverseWords("hi!"));
    }

    @Test
    public void test_multiple_spaces() {
        assertEquals("there hi", reverseWords("hi  there"));
    }

    @Test
    public void test_leading_spaces() {
        assertEquals("there hi", reverseWords("   hi there"));
    }

    @Test
    public void test_trailing_spaces() {
        assertEquals("there hi", reverseWords("hi there   "));
    }

    @Test
    public void test_the_sky_is_blue() {
        assertEquals("blue is sky the", reverseWords("the sky is blue"));
    }
}
