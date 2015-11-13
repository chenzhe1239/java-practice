package com.janosgyerik.practice.oj.leetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniquePathsTest {
    public int uniquePaths(int m, int n) {
        long num = 1;
        long denom = 1;
        // (n + m - 2)! / (n - 1)! / (m - 1)!
        // (n + m - 2 * ... * m)! / (n - 1)!
        for (int i = 0; i < n - 1; ++i) {
            num *= (m + i);
            denom *= (1 + i);
            long div = gcd(num, denom);
            num /= div;
            denom /= div;
        }
        return (int) (num / denom);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Test
    public void test_51_9() {
        assertEquals(1916797311, uniquePaths(51, 9));
    }
}
