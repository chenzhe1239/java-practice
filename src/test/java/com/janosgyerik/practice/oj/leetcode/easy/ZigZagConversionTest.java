package com.janosgyerik.practice.oj.leetcode.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZigZagConversionTest {

    public String convert(String s, int nRows) {
        // 0   4   8  % n * 2 - 2 == 0
        // 1 3 5 7 9  % n * 2 - 2 == 1
        // 2   6      % n * 2 - 2 == 2
        //
        // P     I     N  0     6       12
        // A   L S   I G  1   5 7    11 13
        // Y A   H R      2 4   8 10
        // P     I        3     9
        //
        // P     I     N  0:0         6:1           12:2
        // A   L S   I G  1:3    5:4  7:5      11:6 13:7
        // Y A   H R      2:8  4:9    8:10  10:11
        // P     I        3:12        9:13
        //
        // P     I     N  0:0         6:0           12:0
        // A   L S   I G  1:1    5:1  7:1      11:1 13:1
        // Y A   H R      2:2  4:2    8:2  10:2
        // P     I        3:3         9:3
        //
        // 0       8
        // 1     7 9
        // 2   6
        // 3 5
        // 4
        int len = s.length();
        if (len <= nRows || nRows == 1) {
            return s;
        }
        int x = 2 * nRows - 2;
        StringBuilder[] rows = new StringBuilder[nRows];
        for (int i = 0; i < nRows; ++i) {
            rows[i] = new StringBuilder();
        }
        for (int i = 0, row = 0; i < len; ++i) {
            rows[row].append(s.charAt(i));
            int direction = i % x < x / 2 ? 1 : -1;
            row += direction;
        }
        StringBuilder builder = new StringBuilder(len);
        for (StringBuilder row : rows) {
            builder.append(row.toString());
        }
        return builder.toString();
    }

    @Test
    public void testPayPalIsHiring_3() {
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
    }

    @Test
    public void testPayPalIsHiring_4() {
        assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
    }

    @Test
    public void test_A_1() {
        assertEquals("A", convert("A", 1));
    }
}
