package com.janosgyerik.practice.oj.leetcode.medium.CountNumbersWithUniqueDigits;

public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        switch (n) {
            case 0:
                return 1;
            case 1:
                return 10;
        }
        return 9 * nChooseK(9, 9 - n + 1) + countNumbersWithUniqueDigits(n - 1);
    }

    private int nChooseK(int start, int end) {
        return start > end ? start * nChooseK(start - 1, end) : 1;
    }
}
