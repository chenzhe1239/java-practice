package com.janosgyerik.practice.oj.leetcode.hard.FirstMissingPositive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class StringUtils {
    public static final char LF = '\n';
    public static final char CR = '\r';

    public static boolean equalsIgnoreNewlineTwirks(String str, String other) {
        if (str == null || other == null) {
            return false;
        }
        if (str == other) {
            return true;
        }

        char[] s1 = str.toCharArray();
        char[] s2 = other.toCharArray();
        int index1 = 0, index2 = 0;
        while (true) {
            boolean oob1 = index1 >= s1.length, oob2 = index2 >= s2.length;
            if (oob1 | oob2) {
                return oob1 & oob2;
            }

            char ch1 = s1[index1], ch2 = s2[index2];
            if (ch1 != ch2) {
                if (ch1 != LF && ch1 != CR) {
                    return false;
                }
                if (ch2 != LF && ch2 != CR) {
                    return false;
                }

                if (index1 + 1 < s1.length && isCRAndLF(s1[index1], s1[index1 + 1])) {
                    index1++;
                }
                if (index2 + 1 < s2.length && isCRAndLF(s2[index2], s2[index2 + 1])) {
                    index2++;
                }
            }

            index1++;
            index2++;
        }
    }

    public static boolean equalsIgnoreNewlineTwirks2(String str, String other) {
        if (str == null || other == null) {
            return false;
        }
        if (str == other) {
            return true;
        }

        char[] s1 = str.toCharArray();
        char[] s2 = other.toCharArray();
        int index1 = 0, index2 = 0;
        while (true) {
            boolean oob1 = index1 >= s1.length, oob2 = index2 >= s2.length;
            if (oob1 | oob2) {
                return oob1 & oob2;
            }

            char ch1 = s1[index1], ch2 = s2[index2];
            if (ch1 != ch2) {
                if (ch1 != LF && ch1 != CR) {
                    return false;
                }
                if (ch2 != LF && ch2 != CR) {
                    return false;
                }

                if (index1 + 1 < s1.length && isCRAndLF(s1[index1], s1[index1 + 1])) {
                    index1++;
                }
                if (index2 + 1 < s2.length && isCRAndLF(s2[index2], s2[index2 + 1])) {
                    index2++;
                }
            }

            index1++;
            index2++;
        }
    }

    private static boolean isCRAndLF(char ch1, char ch2) {
        return (ch1 == CR && ch2 == LF) || (ch1 == LF && ch2 == CR);
    }
}

public class StringUtilsTest {
    @Test
    public void test_same() {
        assertEquals(true, StringUtils.equalsIgnoreNewlineTwirks("hello", "hello"));
    }

    @Test
    public void test_same_with_newline_at_end() {
        assertEquals(true, StringUtils.equalsIgnoreNewlineTwirks("hello\r", "hello\n"));
    }

    @Test
    public void test_same_with_newline_at_end2() {
        assertEquals(true, StringUtils.equalsIgnoreNewlineTwirks("he\r\nllo", "he\n\rllo"));
    }

    @Test
    public void test_not_same_with_excess_newline_at_end() {
        assertEquals(false, StringUtils.equalsIgnoreNewlineTwirks("hello\r", "hello\n\n"));
    }

    @Test
    public void test_not_same_with_newline_in_middle() {
        assertEquals(false, StringUtils.equalsIgnoreNewlineTwirks("he\nllo", "hello"));
    }

    @Test
    public void test_not_same_with_newline_at_end() {
        assertEquals(false, StringUtils.equalsIgnoreNewlineTwirks("hello", "hello\n\n"));
    }
}
