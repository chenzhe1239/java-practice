package com.janosgyerik.practice.oj.leetcode.medium.OnesAndZeros;

import java.util.Arrays;

public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length < 10) {
            return findMaxFormDP(strs, m, n);
        }
        Arrays.sort(strs, (a, b) -> {
            int lencmp = Integer.compare(a.length(), b.length());
            if (lencmp != 0) {
                return lencmp;
            }
            int zeros1 = countZeros(a);
            int ones1 = a.length() - zeros1;
            int zeros2 = countZeros(b);
            int ones2 = b.length() - zeros2;
            return Integer.compare(Math.max(zeros1, ones1), Math.max(zeros2, ones2));
        });
        int count = 0;
        for (String s : strs) {
            int zeros = countZeros(s);
            int ones = s.length() - zeros;
            if (zeros <= m && ones <= n) {
                count++;
                m -= zeros;
                n -= ones;
            }
        }
        return count;
    }

    public int findMaxFormDP(String[] strs, int m, int n) {
        int[] zeros = new int[strs.length];
        int[] ones = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            zeros[i] = countZeros(strs[i]);
            ones[i] = strs[i].length() - zeros[i];
        }
        return findMax(zeros, ones, m, n, 0, 0);
    }

    int findMax(int[] zeros, int[] ones, int m, int n, int i, int count) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (m == 0 && n == 0 || i == zeros.length) {
            return count;
        }
        return Math.max(
                findMax(zeros, ones, m, n, i + 1, count),
                findMax(zeros, ones, m - zeros[i], n - ones[i], i + 1, count + 1)
        );
    }

    int countZeros(String s) {
        int count = 0;
        for (char x : s.toCharArray()) {
            if (x == '0') {
                count++;
            }
        }
        return count;
    }
}
